package net.anasa.util.ui;

import javax.swing.Icon;

import net.anasa.util.ICallback;
import net.anasa.util.Progress;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class SplashScreenComponent extends WindowComponent
{
	public SplashScreenComponent(Icon icon, Progress progress, ICallback complete)
	{
		// setAlwaysOnTop(true);
		setFrameVisible(false);
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.CENTER, new LabelComponent(icon));
		layout.set(BorderPosition.BOTTOM, new ProgressBarComponent(progress));
		layout.apply(this);
		
		new Thread(() -> {
			while(!progress.isComplete());
			complete.call();
			close();
		}).start();
	}
}
