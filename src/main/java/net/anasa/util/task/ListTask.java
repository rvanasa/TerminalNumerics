package net.anasa.util.task;

import java.util.Collection;

import net.anasa.util.Listing;
import net.anasa.util.Listing.IListAction;
import net.anasa.util.Progress;

public class ListTask<T> implements ITask
{
	private final String name;
	
	private final Collection<T> items;
	
	private final IListAction<T> action;
	
	public ListTask(String name, Listing<T> items, IListAction<T> action)
	{
		this(name, items.getValues(), action);
	}
	
	public ListTask(String name, Collection<T> items, IListAction<T> action)
	{
		this.name = name;
		
		this.items = items;
		
		this.action = action;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String getName(Progress progress)
	{
		return getName();
	}

	public Collection<T> getItems()
	{
		return items;
	}
	
	public IListAction<T> getAction()
	{
		return action;
	}
	
	@Override
	public int getSize()
	{
		return getItems().size();
	}
	
	@Override
	public void runTask(Progress progress)
	{
		for(T item : getItems())
		{
			getAction().act(item);
			progress.increment();
		}
	}
}
