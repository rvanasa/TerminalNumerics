package net.anasa.math.test;

import javax.swing.ImageIcon;

import net.anasa.util.Progress;
import net.anasa.util.ui.SplashScreenComponent;
import net.anasa.util.ui.WindowComponent;

public class Test
{
	public static void main(String... argv) throws Exception
	{
		WindowComponent window = new WindowComponent("Test Display", new SplashScreenComponent(new ImageIcon("E:/Pictures/Anasa/AnasaRenderVingette_Thumbnail.jpg"), Progress.start("test", 10), () -> {
			for(int i = 0; i < 10; i++)
			{
				try
				{
					Thread.sleep(100);
					Progress.increment("test");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}));
		window.setFrameVisible(false);
		window.display();
	}
}
