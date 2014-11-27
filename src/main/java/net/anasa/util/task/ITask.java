package net.anasa.util.task;

import net.anasa.util.Progress;

public interface ITask
{
	public int getSize();
	
	public void runTask(Progress progress);
	
	public String getName(Progress progress);
	
	default Progress start()
	{
		Progress progress = new Progress(getSize());
		
		new Thread(() -> {
			runTask(progress);
			progress.setComplete();
		}).start();
		
		return progress;
	}
}
