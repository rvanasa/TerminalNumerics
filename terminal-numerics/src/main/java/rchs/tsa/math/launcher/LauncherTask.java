package rchs.tsa.math.launcher;

import java.io.File;

import net.anasa.util.task.ComplexTask;
import net.anasa.util.task.DirectoryTask;
import net.anasa.util.task.Task;
import net.anasa.util.ui.UI;
import rchs.tsa.math.resource.ResourceType;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.resource.module.internal.ui.UIModule;

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
		
		addTask(new DownloadTask(context));
		
		for(ResourceType type : ResourceType.values())
		{
			addTask(new DirectoryTask("Loading " + type.getPath(), new File(dir, "resources/" + type.getPath()), (file) -> file.getName().endsWith("." + type.getExtension()), (file) -> {
				try
				{
					type.register(context, file);
				}
				catch(Exception e)
				{
					UI.sendError("Failed to load resource from file: " + file.getName(), e);
				}
			}));
		}
		
		addTask(new Task("Verifying dependencies", () -> {
			context.verifyResources();
		}));
	}
}
