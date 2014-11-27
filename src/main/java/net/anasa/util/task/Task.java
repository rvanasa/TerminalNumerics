package net.anasa.util.task;

import java.util.Collection;

import net.anasa.util.Listing;
import net.anasa.util.Progress;

public class Task<T> implements ITask
{
	private final String name;
	
	private final Collection<T> items;
	
	private final ITaskProcess<T> process;
	
	public Task(String name, Listing<T> items, ITaskProcess<T> process)
	{
		this(name, items.getValues(), process);
	}
	
	public Task(String name, Collection<T> items, ITaskProcess<T> process)
	{
		this.name = name;
		
		this.items = items;
		
		this.process = process;
	}
	
	public String getName()
	{
		return name;
	}

	public Collection<T> getItems()
	{
		return items;
	}
	
	public ITaskProcess<T> getProcess()
	{
		return process;
	}
	
	@Override
	public int getSize()
	{
		return getItems().size();
	}
	
	@Override
	public void processItems(Progress progress)
	{
		for(T item : getItems())
		{
			getProcess().processItem(item);
			progress.increment();
		}
	}
}
