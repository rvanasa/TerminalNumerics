package net.anasa.util.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import net.anasa.util.Listing;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.event.IUIListener;
import net.anasa.util.ui.event.WindowCloseEvent;

public class WindowComponent extends UIParentComponent<JFrame>
{
	public static final WindowComponent getParentWindow(IComponent component)
	{
		if(component == null)
		{
			return null;
		}
		else
		{
			Component root = SwingUtilities.getRoot(component.getHandle());
			return WINDOWS.getFirst((window) -> window.getHandle() == root);
		}
	}
	
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
		
		getHandle().addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent event)
			{
				getEvents().dispatch(new WindowCloseEvent(WindowComponent.this));
				
				WINDOWS.remove(WindowComponent.this);
				if(WINDOWS.isEmpty())
				{
					System.exit(0);
				}
			}
		});
	}
	
	public void addCloseListener(IUIListener<WindowCloseEvent> listener)
	{
		getEvents().register(WindowCloseEvent.class, listener);
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
	
	public Image getIcon()
	{
		return getHandle().getIconImage();
	}
	
	public void setIcon(Image image)
	{
		getHandle().setIconImage(image);
	}
	
	public void pack()
	{
		getHandle().pack();
	}
	
	public void display()
	{
		if(!getHandle().isPreferredSizeSet())
		{
			pack();
		}
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setPosition(new Vector2(screen.getWidth() / 2 - getSize().getX() / 2 - 20, screen.getHeight() / 2 - getSize().getY() / 2 - 20));
		
		getHandle().setVisible(true);
	}
	
	public void close()
	{
		getHandle().dispose();
	}
}
