package net.anasa.util.ui;

import javax.swing.Icon;

import net.anasa.util.ICallback;
import net.anasa.util.Progress;
import net.anasa.util.task.ITask;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class SplashScreenComponent extends WindowComponent
{
	private final ProgressBarComponent progressBar;
	
	public SplashScreenComponent(Icon icon, ITask task, ICallback callback)
	{
		this(icon, task.start(), callback);
		
		getProgressBar().setText(() -> task.getName(getProgressBar().getProgress()));
	}
	
	public SplashScreenComponent(Icon icon, Progress progress, ICallback callback)
	{
		// setAlwaysOnTop(true);
		setFrameVisible(false);
		
		progressBar = new ProgressBarComponent(progress, () -> {
			callback.call();
			close();
		});
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.CENTER, new LabelComponent(icon));
		layout.set(BorderPosition.BOTTOM, progressBar);
		layout.apply(this);
	}
	
	public ProgressBarComponent getProgressBar()
	{
		return progressBar;
	}
}
