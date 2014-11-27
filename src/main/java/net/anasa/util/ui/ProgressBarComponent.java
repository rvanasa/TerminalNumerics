package net.anasa.util.ui;

import javax.swing.JProgressBar;

import net.anasa.util.Progress;

public class ProgressBarComponent extends UIParentComponent<JProgressBar> implements ISwingComponent
{
	private final Progress progress;
	
	public ProgressBarComponent(Progress progress)
	{
		super(new JProgressBar());
		
		this.progress = progress;
		
		new Thread(() -> {
			while(!getProgress().isComplete())
			{
				if(getProgress().isEnabled())
				{
					getHandle().setMaximum(progress.getMaxValue());
					getHandle().setValue(progress.getValue());
				}
				else
				{
					setWaiting(true);
				}
			}
		}).start();
	}

	public Progress getProgress()
	{
		return progress;
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
