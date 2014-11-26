package net.anasa.math.launcher;

import java.io.File;

import javax.swing.ImageIcon;

import net.anasa.math.MathException;
import net.anasa.math.module.JarModule;
import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.XmlAppLoader;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.module.provided.UIModule;
import net.anasa.math.util.UI;
import net.anasa.util.Listing;
import net.anasa.util.Progress;
import net.anasa.util.data.xml.XmlFile;
import net.anasa.util.logic.IValue;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.MessageComponent;
import net.anasa.util.ui.SplashScreenComponent;
import net.anasa.util.ui.WindowComponent;

public class MathLauncher
{
	private static final String PROGRESS_MODULE = "moduleProgress";
	
	public MathLauncher(File dir, IValue<IComponent> gui) throws MathException
	{
		try
		{
			dir.mkdirs();
			
			File modules = new File(dir, "modules");
			modules.mkdir();
			Listing<File> moduleFiles = new Listing<>(modules.listFiles((path, name) -> name.endsWith(".jar")));
			
			File apps = new File(dir, "apps");
			apps.mkdir();
			Listing<File> appFiles = new Listing<>(apps.listFiles((path, name) -> name.endsWith(".xml")));
			
			Progress.start(PROGRESS_MODULE, moduleFiles.size() + appFiles.size());
			
			loadDefaultModules();
			
			new SplashScreenComponent(new ImageIcon(), PROGRESS_MODULE, () -> {
				try
				{
					moduleFiles.forEach((file) -> {
						try
						{
							getModuleContext().addModule(new JarModule(file));
							Progress.increment(PROGRESS_MODULE);
						}
						catch(Exception e)
						{
							UI.sendError("Failed to load module from file: " + file.getName(), e);
						}
					});
					
					appFiles.forEach((file) -> {
						try
						{
							File imageFile = new File(file.getParent(), file.getName().replaceAll(".xml$", ".png"));
							getModuleContext().addApp(XmlAppLoader.loadApp(getModuleContext(), XmlFile.read(file).getBaseElement(), !imageFile.isFile() ? null : new ImageIcon(imageFile.toURI().toURL()).getImage()));
							Progress.increment(PROGRESS_MODULE);
						}
						catch(Exception e)
						{
							UI.sendError("Failed to load app from file: " + file.getName(), e);
						}
					});
					
					new WindowComponent("Math", gui.getValue()).display();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}).display();
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
		return ModuleContext.getInstance();
	}
}
