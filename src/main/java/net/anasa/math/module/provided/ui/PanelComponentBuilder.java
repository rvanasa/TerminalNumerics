package net.anasa.math.module.provided.ui;

import java.awt.Color;

import net.anasa.util.data.format.IDataFormat;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.PanelComponent;

public class PanelComponentBuilder extends ComponentBuilder<PanelComponent>
{
	ComponentAspect<Integer> background = new ComponentAspect<>("background", IDataFormat.INT, 0xFFFFFF);
	ComponentAspect<Integer> foreground = new ComponentAspect<>("foreground", IDataFormat.INT, 0xFFFFFF);
	
	ComponentAspect<Double> width = new ComponentAspect<>("width", IDataFormat.DOUBLE, 64D);
	ComponentAspect<Double> height = new ComponentAspect<>("height", IDataFormat.DOUBLE, 64D);
	
	@Override
	public PanelComponent build(AspectData data)
	{
		PanelComponent panel = new PanelComponent();
		
		panel.setBackground(new Color(data.getValue(background)));
		panel.setForeground(new Color(data.getValue(foreground)));
		panel.setSize(new Vector2(data.getValue(width), data.getValue(height)));
		
		return panel;
	}
}
