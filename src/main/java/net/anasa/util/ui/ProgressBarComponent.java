package net.anasa.util.ui;

import javax.swing.JProgressBar;

import net.anasa.util.ICallback;
import net.anasa.util.Progress;
import net.anasa.util.logic.IValue;

public class ProgressBarComponent extends UIParentComponent<JProgressBar> implements ISwingComponent
{
	private final Progress progress;
	
	private IValue<String> text;
	
	public ProgressBarComponent(Progress progress)
	{
		this(progress, null);
	}
	
	public ProgressBarComponent(Progress progress, ICallback callback)
	{
		super(new JProgressBar());
		
		this.progress = progress;
		
		setTextVisible(true);
		
		new Thread(() -> {
			while(!getProgress().isComplete())
			{
				String text = getText();
				getHandle().setString(text);
				
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
			
			if(callback != null)
			{
				callback.call();
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
	
	public String getText()
	{
		return text != null ? text.getValue() : null;
	}
	
	public void setText(IValue<String> text)
	{
		this.text = text;
	}
	
	public void setText(String text)
	{
		setText(() -> text);
	}
	
	public boolean isTextVisible()
	{
		return getHandle().isStringPainted();
	}
	
	public void setTextVisible(boolean visible)
	{
		getHandle().setStringPainted(visible);
	}
}
