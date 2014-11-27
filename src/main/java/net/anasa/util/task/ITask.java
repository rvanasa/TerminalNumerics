package net.anasa.util.task;

import net.anasa.util.Progress;

public interface ITask
{
	public int getSize();
	
	public void processItems(Progress progress);
	
	default Progress start()
	{
		Progress progress = new Progress(getSize());
		
		new Thread(() -> {
			processItems(progress);
			progress.setComplete();
		}).start();
		
		return progress;
	}
}
