package net.anasa.util.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;

import net.anasa.util.Debug;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.event.ClickEvent;
import net.anasa.util.ui.event.ClickEvent.ButtonType;
import net.anasa.util.ui.event.IUIListener;
import net.anasa.util.ui.event.UIEventTracker;

public abstract class UIComponent<T extends Component> implements IComponent
{
	static
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			Debug.err("Failed to set look and feel: " + e.getMessage());
		}
	}
	
	private final UIEventTracker events = new UIEventTracker();
	
	private final T handle;
	
	public UIComponent(T handle)
	{
		this.handle = handle;
		
		handle.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent event)
			{
			}
			
			@Override
			public void mousePressed(MouseEvent event)
			{
			}
			
			@Override
			public void mouseExited(MouseEvent event)
			{
			}
			
			@Override
			public void mouseEntered(MouseEvent event)
			{
			}
			
			@Override
			public void mouseClicked(MouseEvent event)
			{
				getEvents().dispatch(new ClickEvent(UIComponent.this, ButtonType.get(event.getButton())));
			}
		});
	}
	
	public UIEventTracker getEvents()
	{
		return events;
	}
	
	public void addClickListener(IUIListener<ClickEvent> listener)
	{
		getEvents().register(ClickEvent.class, listener);
	}
	
	@Override
	public T getHandle()
	{
		return handle;
	}
	
	public int getWidth()
	{
		return (int)getSize().getX();
	}
	
	public int getHeight()
	{
		return (int)getSize().getY();
	}
	
	public Vector2 getSize()
	{
		return new Vector2(getHandle().getSize().getWidth(), getHandle().getSize().getHeight());
	}
	
	public void setSize(Vector2 size)
	{
		getHandle().setPreferredSize(new Dimension((int)size.getX(), (int)size.getY()));
	}
	
	public Color getBackground()
	{
		return getHandle().getBackground();
	}
	
	public void setBackground(Color color)
	{
		getHandle().setBackground(color);
	}
	
	public Color getForeground()
	{
		return getHandle().getForeground();
	}
	
	public void setForeground(Color color)
	{
		getHandle().setForeground(color);
	}
	
	public void setCursor(CursorType type)
	{
		getHandle().setCursor(Cursor.getPredefinedCursor(type.getID()));
	}
	
	public void redraw()
	{
		getHandle().repaint();
	}
	
	public void revalidate()
	{
		getHandle().revalidate();
	}
	
	public enum CursorType
	{
		DEFAULT(Cursor.DEFAULT_CURSOR),
		HAND(Cursor.HAND_CURSOR),
		WAIT(Cursor.WAIT_CURSOR),
		CROSSHAIR(Cursor.CROSSHAIR_CURSOR),
		MOVE(Cursor.MOVE_CURSOR);
		
		private final int id;
		
		private CursorType(int id)
		{
			this.id = id;
		}
		
		public int getID()
		{
			return id;
		}
		
		public static CursorType get(int id)
		{
			for(CursorType type : values())
			{
				if(type.getID() == id)
				{
					return type;
				}
			}
			
			return null;
		}
	}
}
