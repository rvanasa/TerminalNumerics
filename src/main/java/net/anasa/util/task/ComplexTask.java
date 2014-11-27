package net.anasa.util.task;

import net.anasa.util.Listing;

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
	public void processItems()
	{
		for(ITask task : getTasks())
		{
			task.processItems();
		}
	}
}
