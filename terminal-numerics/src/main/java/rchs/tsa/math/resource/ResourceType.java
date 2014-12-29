package rchs.tsa.math.resource;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.data.xml.XmlFile;
import rchs.tsa.math.io.xml.XmlAppLoader;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.ModuleContext;

public enum ResourceType
{
	STANDARDS("props", (context, file) -> context.getStandards().load(Properties.getFrom(new FileInputStream(file)))),
	MODULES("jar", (context, file) -> context.addModule(new JarModuleLoader().load(file))),
	APPS("xml", (context, file) -> {
		File imageFile = new File(file.getParent(), file.getName().replaceAll(".xml$", ".png"));
		Image image = !imageFile.isFile() ? null : new ImageIcon(imageFile.toURI().toURL()).getImage();
		context.addApp(new XmlAppLoader(context, image).load(XmlFile.read(file).getBaseElement()));
	});
	
	private final String extension;
	private final IResourceTypeHandle handle;
	
	private ResourceType(String extension, IResourceTypeHandle handle)
	{
		this.extension = extension;
		this.handle = handle;
	}
	
	public String getExtension()
	{
		return extension;
	}
	
	public String getPath()
	{
		return name().toLowerCase();
	}
	
	public void register(ModuleContext context, File file) throws ModuleException
	{
		try
		{
			handle.register(context, file);
		}
		catch(Exception e)
		{
			throw new ModuleException("Failed to register file resource: " + file.getName() + " (" + name() + ")", e);
		}
	}
	
	private interface IResourceTypeHandle
	{
		public void register(ModuleContext context, File file) throws Exception;
	}
}
