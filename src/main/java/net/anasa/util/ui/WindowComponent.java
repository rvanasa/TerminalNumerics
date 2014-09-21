package net.anasa.util.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.anasa.util.Listing;
import net.anasa.util.math.Vector2;

public class WindowComponent extends UIParentComponent<JFrame>
{
	private static final Listing<WindowComponent> WINDOWS = new Listing<>();
	
	public WindowComponent()
	{
		this(null, null, true);
	}
	
	public WindowComponent(String title)
	{
		this(title, null);
	}
	
	public WindowComponent(String title, IComponent content)
	{
		this(title, content, true);
	}
	
	public WindowComponent(IComponent content)
	{
		this(null, content, false);
	}
	
	public WindowComponent(String title, IComponent content, boolean frame)
	{
		super(new JFrame());
		
		setTitle(title);
		setFrameVisible(frame);
		setComponent(content, null);
		
		getHandle().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		WINDOWS.add(this);
		
		getHandle().addWindowStateListener((event) -> {
			if(event.getNewState() == WindowEvent.WINDOW_CLOSED)
			{
				WINDOWS.remove(this);
				if(WINDOWS.isEmpty())
				{
					System.exit(0);
				}
			}
		});
	}
	
	public void setContent(IParentComponent content)
	{
		getHandle().setContentPane(content.getHandle());
	}
	
	public String getTitle()
	{
		return getHandle().getTitle();
	}
	
	public void setTitle(String title)
	{
		getHandle().setTitle(title);
	}
	
	public Vector2 getPosition()
	{
		return new Vector2(getHandle().getX(), getHandle().getY());
	}
	
	public void setPosition(Vector2 position)
	{
		getHandle().setLocation((int)position.getX(), (int)position.getY());
	}
	
	public Vector2 getMinSize()
	{
		return new Vector2(getHandle().getMinimumSize().getWidth(), getHandle().getMinimumSize().getHeight());
	}
	
	public void setMinSize(Vector2 size)
	{
		getHandle().setMinimumSize(new Dimension((int)size.getX(), (int)size.getY()));
	}
	
	public void setAlwaysOnTop(boolean alwaysOnTop)
	{
		getHandle().setAlwaysOnTop(alwaysOnTop);
	}
	
	public void setResizable(boolean resizable)
	{
		getHandle().setResizable(resizable);
	}
	
	public void setFrameVisible(boolean frame)
	{
		getHandle().setUndecorated(!frame);
	}
	
	public void pack()
	{
		getHandle().pack();
	}
	
	public void display()
	{
		pack();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setPosition(new Vector2(screen.getWidth() / 2 - getSize().getX() / 2 - 20, screen.getHeight() / 2 - getSize().getY() / 2 - 20));
		
		getHandle().setVisible(true);
	}
	
	public void close()
	{
		getHandle().dispose();
	}
}
