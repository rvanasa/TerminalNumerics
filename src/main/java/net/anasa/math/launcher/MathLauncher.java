package net.anasa.math.launcher;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;

import net.anasa.math.MathException;
import net.anasa.math.module.JarModule;
import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.XmlAppLoader;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.module.provided.ui.UIModule;
import net.anasa.math.util.StateStandards;
import net.anasa.math.util.UI;
import net.anasa.util.Listing;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.data.xml.XmlFile;
import net.anasa.util.logic.IValue;
import net.anasa.util.task.ComplexTask;
import net.anasa.util.task.ITask;
import net.anasa.util.task.Task;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.MessageComponent;
import net.anasa.util.ui.SplashScreenComponent;
import net.anasa.util.ui.WindowComponent;

public class MathLauncher
{
	private final ModuleContext context;
	
	public MathLauncher(ModuleContext context, IValue<IComponent> gui) throws MathException
	{
		this.context = context;
		
		File dir = context.getDirectory();
		
		try
		{
			dir.mkdirs();
			
			loadDefaultModules();
			
			File modules = new File(dir, "modules");
			modules.mkdir();
			ITask loadModules = new Task<>("Loading modules", new Listing<>(modules.listFiles((path, name) -> name.endsWith(".jar"))), (file) -> {
				try
				{
					getModuleContext().addModule(new JarModule(file));
				}
				catch(Exception e)
				{
					UI.sendError("Failed to load module from file: " + file.getName(), e);
				}
			});
			
			File standards = new File(dir, "standards");
			standards.mkdir();
			ITask loadStandards = new Task<>("Loading standards", new Listing<>(standards.listFiles()), (file) -> {
				try
				{
					StateStandards.loadModel(Properties.getFrom(new FileInputStream(file)));
				}
				catch(Exception e)
				{
					UI.sendError("Failed to load standard from file: " + file.getName(), e);
				}
			});
			
			File apps = new File(dir, "apps");
			apps.mkdir();
			ITask loadApps = new Task<>("Loading apps", new Listing<>(apps.listFiles((path, name) -> name.endsWith(".xml"))), (file) -> {
				try
				{
					File imageFile = new File(file.getParent(), file.getName().replaceAll(".xml$", ".png"));
					XmlAppLoader loader = new XmlAppLoader(getModuleContext(), !imageFile.isFile() ? null : new ImageIcon(imageFile.toURI().toURL()).getImage());
					getModuleContext().addApp(loader.load(XmlFile.read(file).getBaseElement()));
				}
				catch(Exception e)
				{
					UI.sendError("Failed to load app from file: " + file.getName(), e);
				}
			});
			
			ITask task = new ComplexTask(loadModules, loadStandards, loadApps);
			
			new SplashScreenComponent(new ImageIcon(getClass().getResource("/ui/splash_screen.png")), task.start(), () -> new WindowComponent("Math Software", gui.getValue()).display()).display();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			new MessageComponent("An internal error has occurred. Please notify the developers.\n(" + e + ")").display();
			System.exit(1);
		}
	}
	
	private void loadDefaultModules() throws ModuleException
	{
		getModuleContext().addModule(new UIModule());
	}
	
	public ModuleContext getModuleContext()
	{
		return context;
	}
}
