package rchs.tsa.math.launcher;

import java.io.IOException;

import net.anasa.util.Debug;
import net.anasa.util.task.ComplexTask;
import net.anasa.util.task.Task;
import net.anasa.util.ui.UI;
import rchs.tsa.math.resource.ResourceType;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.ModuleContext;

public class DownloadTask extends ComplexTask
{
	public DownloadTask(ModuleContext context)
	{
		for(ResourceType type : ResourceType.values())
		{
			addTask(new Task("Installing available " + type.getPath(), () -> {
				if(context.getDownloader() == null)
				{
					Debug.log("Skipping resource download");
					return;
				}
				try
				{
					for(String id : context.getDownloader().getAvailableResources(type))
					{
						try
						{
							context.downloadResource(id, type);
						}
						catch(ModuleException e)
						{
							UI.sendError("Failed to install resources!", e);
						}
					}
				}
				catch(IOException e)
				{
					Debug.err("Failed to retrieve resources!");
					e.printStackTrace();
				}
			}));
		}
	}
}
