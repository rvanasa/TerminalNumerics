package rchs.tsa.math.resource;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.ILoader;
import net.anasa.util.data.properties.Properties;
import rchs.tsa.math.resource.module.IModule;
import rchs.tsa.math.resource.module.IModuleDelegate;
import rchs.tsa.math.resource.module.Module;
import rchs.tsa.math.resource.module.ModuleException;

public class JarModuleLoader implements ILoader<File, IModule>
{
	@Override
	public IModule load(File file) throws FormatException
	{
		try(JarFile jar = new JarFile(file))
		{
			ZipEntry propsEntry = jar.getEntry("module.props");
			Checks.checkNotNull(propsEntry, new ModuleException("Could not find module.props file in jar: " + file.getName()));
			
			Properties props = Properties.getFrom(jar.getInputStream(propsEntry));
			String id = props.getString("id");
			Version version = Version.getFrom(props.getString("version"));
			String name = props.getString("name");
			String description = props.getString("description", null);
			
			IModuleDelegate delegate = loadClass(IModuleDelegate.class, file, props.getString("delegate")).newInstance();
			
			Dependency[] dependencies = new Listing<>(props.getString("dependencies", "").split(",|;"))
					.filter((data) -> !data.trim().isEmpty())
					.conform((data) -> Dependency.getFrom(data))
					.toArray(Dependency.class);
			
			return new Module(id, version, name, description, dependencies, delegate);
		}
		catch(Exception e)
		{
			throw new FormatException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Class<T> loadClass(Class<T> clazz, File file, String name) throws FormatException
	{
		try(URLClassLoader loader = new URLClassLoader(new URL[] {new URL("jar:file:" + file.getPath() + "!/")}))
		{
			return (Class<T>)Class.forName(name, true, loader);
		}
		catch(Throwable e)
		{
			throw new FormatException("Failed to load class from file: " + file, e);
		}
	}
}
