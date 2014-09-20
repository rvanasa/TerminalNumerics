package net.anasa.util.ui;

import javax.swing.JProgressBar;

import net.anasa.util.Progress;

public class ProgressBarComponent extends UIParentComponent<JProgressBar> implements ISwingComponent
{
	private final String progressID;
	
	public ProgressBarComponent(Progress progress)
	{
		super(new JProgressBar(JProgressBar.HORIZONTAL, progress.getMax()));
		
		this.progressID = progress.getID();
		
		new Thread(() -> {
			while(!Progress.isComplete(getProgressID()))
			{
				getHandle().setValue(Progress.get(getProgressID()).getValue());
			}
		}).start();
	}

	public String getProgressID()
	{
		return progressID;
	}

	public boolean isWaiting()
	{
		return getHandle().isIndeterminate();
	}

	public void setWaiting(boolean waiting)
	{
		getHandle().setIndeterminate(waiting);
	}
}
