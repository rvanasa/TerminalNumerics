package net.anasa.util.ui;

import javax.swing.JProgressBar;

import net.anasa.util.Progress;

public class ProgressBarComponent extends UIParentComponent<JProgressBar> implements ISwingComponent
{
	private final String progress;
	
	public ProgressBarComponent(String progress)
	{
		super(new JProgressBar());
		
		this.progress = progress;
		
		new Thread(() -> {
			while(!Progress.isComplete(getProgress()))
			{
				Progress current = Progress.get(getProgress());
				if(current != null)
				{
					getHandle().setMaximum(current.getMax());
					getHandle().setValue(current.getValue());
				}
				else
				{
					setWaiting(false);
				}
			}
		}).start();
	}

	public String getProgress()
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
