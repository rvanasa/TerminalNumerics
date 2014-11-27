package net.anasa.util.task;

import net.anasa.util.Listing;
import net.anasa.util.Progress;

public class ComplexTask implements ITask
{
	private final Listing<ITask> tasks;
	
	public ComplexTask(ITask... tasks)
	{
		this(new Listing<>(tasks));
	}
	
	public ComplexTask(Listing<ITask> tasks)
	{
		this.tasks = tasks;
	}
	
	public Listing<ITask> getTasks()
	{
		return tasks;
	}
	
	@Override
	public int getSize()
	{
		int size = 0;
		for(ITask task : getTasks())
		{
			size += task.getSize();
		}
		
		return size;
	}
	
	@Override
	public void runTask(Progress progress)
	{
		for(ITask task : getTasks())
		{
			task.runTask(progress);
		}
	}
	
	public ITask getCurrentTask(Progress progress)
	{
		int value = progress.getValue();
		int ct = 0;
		for(ITask task : getTasks())
		{
			ct += task.getSize();
			if(ct > value)
			{
				return task;
			}
		}
		
		return null;
	}
	
	@Override
	public String getName(Progress progress)
	{
		ITask task = getCurrentTask(progress);
		
		return task == null ? null : task.getName(progress);
	}
}
