package net.anasa.util.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import net.anasa.util.ui.event.DrawEvent;
import net.anasa.util.ui.event.IUIListener;

public class PanelComponent extends UIParentComponent<JPanel> implements ISwingComponent
{
	public PanelComponent()
	{
		super(new CPanel());
		((CPanel)getHandle()).component = this;
	}
	
	public PanelComponent(IComponent component)
	{
		this();
		
		setComponent(component, null);
	}
	
	public void addDrawListener(IUIListener<DrawEvent> listener)
	{
		getEvents().register(DrawEvent.class, listener);
	}
	
	private static class CPanel extends JPanel
	{
		private PanelComponent component;
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			component.getEvents().dispatch(new DrawEvent(component, (Graphics2D)g));
		}
	}
}
