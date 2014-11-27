package net.anasa.util.task;

import net.anasa.util.ICallback;
import net.anasa.util.Progress;

public class Task implements ITask
{
	private final String name;
	
	private final ICallback callback;
	
	public Task(String name, ICallback callback)
	{
		this.name = name;
		this.callback = callback;
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
	
	public ICallback getCallback()
	{
		return callback;
	}
	
	@Override
	public int getSize()
	{
		return 1;
	}
	
	@Override
	public void runTask(Progress progress)
	{
		getCallback().call();
	}
}
