package rchs.tsa.math;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import rchs.tsa.math.launcher.MathLauncher;
import rchs.tsa.math.module.context.ModuleContext;
import rchs.tsa.math.ui.MathContainerComponent;
import net.anasa.util.Debug;
import net.anasa.util.data.io.FileHandler;
import net.anasa.util.data.io.IOHelper;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.UI;

public final class TerminalNumerics
{
	public static void main(String... argv) throws Exception
	{
		File settingsFile;
		
		if(argv.length > 0)
		{
			settingsFile = new File(argv[0]);
			if(!settingsFile.isFile())
			{
				throw new IOException("Could not find settings file at location: " + settingsFile.getPath());
			}
		}
		else
		{
			settingsFile = new File("data/settings.txt");
			settingsFile.getParentFile().mkdirs();
			settingsFile.createNewFile();
		}
		
		File logDir = new File(settingsFile.getParent(), "logs");
		logDir.mkdir();
		
		try
		{
			File logFile = new File(logDir, "log.txt");
			
			long size = logFile.length();
			if(logFile.exists() && size > 500 * 1000)
			{
				int ct = 0;
				File destination;
				do
				{
					ct++;
					destination = new File(logDir, "log." + ct + ".txt");
				}
				while(destination.exists());
				
				Debug.log("Storing log file: " + destination.getName() + " (file size: " + size + ")");
				
				Files.move(logFile.toPath(), destination.toPath());
				logFile.createNewFile();
			}
			
			Debug.registerListener((type, message) -> {
				try(FileWriter output = new FileWriter(logFile, true))
				{
					output.write(message + '\n');
					output.flush();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			});

			Debug.log("Settings file: " + settingsFile.getPath());
			Debug.log("[" + new Date() + "]");
			
			new TerminalNumerics(settingsFile);
			
			new MathLauncher(getContext(), () -> new MathContainerComponent(getContext()));
		}
		catch(Exception e)
		{
			UI.sendError("The software encountered a fatal error!", e);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static TerminalNumerics INSTANCE;
	
	public static TerminalNumerics getInstance()
	{
		return INSTANCE;
	}
	
	public static String getLicense()
	{
		return getInstance().license;
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
	
	private final String license;
	
	private final Properties settings;
	private final File directory;
	
	private final ModuleContext context;
	
	private TerminalNumerics(File settingsFile) throws IOException
	{
		INSTANCE = this;
		
		license = IOHelper.read(getClass().getResourceAsStream("/license.txt"));
		
		settings = new FileHandler<>(settingsFile, Properties.FORMAT).load();
		directory = new File(settings.getString("directory", settingsFile.getParent()));
		
		context = new ModuleContext();
	}
}
