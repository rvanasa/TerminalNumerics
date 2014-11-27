package net.anasa.math;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import net.anasa.math.launcher.MathLauncher;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.ui.app.AppListComponent;
import net.anasa.math.util.UI;
import net.anasa.util.Debug;
import net.anasa.util.data.io.FileHandler;
import net.anasa.util.data.properties.Properties;

public final class MathSoftware
{
	public static void main(String... argv)
	{
		File settingsFile = new File("data/settings.props");
		
		try
		{
			Debug.registerListener((message, type) -> {
				try(FileWriter output = new FileWriter(new File(settingsFile.getParent(), "log.txt"), true))
				{
					output.write(message + '\n');
					output.flush();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			});
			
			Debug.log("[" + new Date() + "]");
			
			new MathSoftware(settingsFile);
			
			new MathLauncher(getContext(), () -> new AppListComponent(getContext().getApps().getApps()));
		}
		catch(Exception e)
		{
			UI.sendError("The software encountered a fatal error!", e);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static MathSoftware INSTANCE;
	
	public static MathSoftware getInstance()
	{
		return INSTANCE;
	}
	
	public static Properties getSettings()
	{
		return getInstance().settings;
	}
	
	public static File getDirectory()
	{
		return getInstance().directory;
	}
	
	public static ModuleContext getContext()
	{
		return getInstance().context;
	}
	
	private final Properties settings;
	private final File directory;
	
	private final ModuleContext context;
	
	private MathSoftware(File settingsFile) throws IOException
	{
		INSTANCE = this;
		
		settings = new FileHandler<>(settingsFile, Properties.FORMAT).load();
		directory = new File(settings.getString("directory", settingsFile.getParent()));
		
		context = new ModuleContext();
	}
}
