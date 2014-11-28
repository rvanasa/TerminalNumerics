package net.anasa.math.module;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.data.properties.Properties;

public class JarModule implements IModule
{
	private final Properties props;
	
	private final String id;
	private final Version version;
	private final String name;
	private final String description;
	
	private final IModuleDelegate delegate;
	
	private final Dependency[] dependencies;
	
	public JarModule(File file) throws ModuleException
	{
		try(JarFile jar = new JarFile(file))
		{
			ZipEntry propsEntry = jar.getEntry("module.props");
			Checks.checkNotNull(propsEntry, new ModuleException("Could not find module.props file in jar: " + file.getName()));
			
			props = Properties.getFrom(jar.getInputStream(propsEntry));
			id = props.getString("id");
			version = Version.getFrom(props.getString("version"));
			name = props.getString("name");
			description = props.getString("description", null);
			
			URLClassLoader loader = new URLClassLoader(new URL[] {new URL("jar:file:" + file.getPath() + "!/")});
			
			@SuppressWarnings("unchecked")
			Class<? extends IModuleDelegate> componentClass = (Class<? extends IModuleDelegate>)Class.forName(props.getString("delegate"), true, loader);
			delegate = componentClass.newInstance();
			
			dependencies = new Listing<>(props.getString("dependencies", "").split(",|;"))
					.filter((data) -> !data.trim().isEmpty())
					.conform((data) -> Dependency.getFrom(data))
					.toArray(Dependency.class);
			
			loader.close();
			jar.close();
		}
		catch(Exception e)
		{
			throw new ModuleException(e);
		}
	}
	
	@Override
	public String getID()
	{
		return id;
	}
	
	@Override
	public Version getVersion()
	{
		return version;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public String getDescription()
	{
		return description;
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return delegate;
	}

	@Override
	public Dependency[] getDependencies()
	{
		return dependencies;
	}
}
