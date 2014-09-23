package net.anasa.math.launcher;

import java.io.File;

import javax.swing.ImageIcon;

import net.anasa.math.MathException;
import net.anasa.math.module.JarModule;
import net.anasa.math.module.ModuleException;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.module.provided.UIModule;
import net.anasa.math.ui.TransformationComponent;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.Progress;
import net.anasa.util.ui.MessageComponent;
import net.anasa.util.ui.SplashScreenComponent;
import net.anasa.util.ui.WindowComponent;

public class MathLauncher
{
	public MathLauncher(File dir) throws MathException
	{
		try
		{
			dir.mkdirs();
			
			File modules = new File(dir, "modules");
			modules.mkdir();
			
			Listing<File> files = new Listing<>(modules.listFiles((path, name) -> name.endsWith(".jar")));
			
			getModuleContext().addModule(new UIModule());
			
			Progress.start(ModuleContext.PROGRESS_MODULE, files.size());
			
			new SplashScreenComponent(new ImageIcon(), ModuleContext.PROGRESS_MODULE, () -> {
				try
				{
					files.forEach((file) -> {
						try
						{
							Progress.increment(ModuleContext.PROGRESS_MODULE);
							getModuleContext().addModule(new JarModule(file));
						}
						catch(ModuleException e)
						{
							Debug.log("Failed to load module from file: " + file.getName());
							e.printStackTrace();
						}
					});
					new WindowComponent("Graph Interface", new TransformationComponent()).display();
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
	
	public ModuleContext getModuleContext()
	{
		return ModuleContext.getInstance();
	}
}
