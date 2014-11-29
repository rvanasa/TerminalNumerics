package rchs.tsa.math.launcher;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;

import net.anasa.util.logic.IValue;
import net.anasa.util.task.ITask;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.MessageComponent;
import net.anasa.util.ui.SplashScreenComponent;
import net.anasa.util.ui.WindowComponent;
import rchs.tsa.math.MathException;
import rchs.tsa.math.module.context.ModuleContext;

public class MathLauncher
{
	private final ModuleContext context;
	
	public MathLauncher(ModuleContext context, IValue<IComponent> gui) throws MathException
	{
		this.context = context;
		
		File dir = context.getDirectory();
		dir.mkdirs();
		
		try
		{
			ITask task = new LauncherTask(context, dir);
			Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ui/thumbnail.png"));
			SplashScreenComponent screen = new SplashScreenComponent(new ImageIcon(getClass().getResource("/ui/splash_screen.png")), task, () -> new WindowComponent("Terminal Numerics", icon, gui.getValue()).display());
			screen.setIcon(icon);
			screen.getProgressBar().setForeground(new Color(0x444444));
			screen.display();
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
		return context;
	}
}
