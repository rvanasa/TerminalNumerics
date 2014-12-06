package rchs.tsa.math.launcher;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.data.xml.XmlFile;
import net.anasa.util.task.ComplexTask;
import net.anasa.util.task.DirectoryTask;
import net.anasa.util.task.Task;
import net.anasa.util.ui.UI;
import rchs.tsa.math.io.xml.XmlAppLoader;
import rchs.tsa.math.module.JarModule;
import rchs.tsa.math.module.context.ModuleContext;
import rchs.tsa.math.module.provided.ui.UIModule;
import rchs.tsa.math.util.StateStandards;

public class LauncherTask extends ComplexTask
{
	public LauncherTask(ModuleContext context, File dir)
	{
		addTask(new Task(null, () -> {
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}));
		
		addTask(new Task("Loading internal data", () -> {
			try
			{
				context.addModule(new UIModule());
			}
			catch(Exception e)
			{
				UI.sendError("Failed to load internal data!", e);
				System.exit(1);
			}
		}));
		
		addTask(new DirectoryTask("Loading modules", new File(dir, "modules"), (file) -> file.getName().endsWith(".jar"), (file) -> {
			try
			{
				context.addModule(new JarModule(file));
			}
			catch(Exception e)
			{
				UI.sendError("Failed to load module from file: " + file.getName(), e);
			}
		}));
		
		addTask(new DirectoryTask("Loading standards", new File(dir, "standards"), (file) -> {
			try
			{
				StateStandards.load(Properties.getFrom(new FileInputStream(file)));
			}
			catch(Exception e)
			{
				UI.sendError("Failed to load standard from file: " + file.getName(), e);
			}
		}));
		
		addTask(new DirectoryTask("Loading apps", new File(dir, "apps"), (file) -> file.getName().endsWith(".xml"), (file) -> {
			try
			{
				File imageFile = new File(file.getParent(), file.getName().replaceAll(".xml$", ".png"));
				XmlAppLoader loader = new XmlAppLoader(context, !imageFile.isFile() ? null : new ImageIcon(imageFile.toURI().toURL()).getImage());
				context.addApp(loader.load(XmlFile.read(file).getBaseElement()));
			}
			catch(Exception e)
			{
				UI.sendError("Failed to load app from file: " + file.getName(), e);
			}
		}));
		
		addTask(new Task("Verifying dependencies", () -> {
			context.verifyResources();
		}));
	}
}
