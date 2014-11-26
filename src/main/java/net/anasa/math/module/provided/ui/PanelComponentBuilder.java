package net.anasa.math.module.provided.ui;

import java.awt.Color;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.PanelComponent;

public class PanelComponentBuilder extends ComponentBuilder<PanelComponent>
{
	ComponentAspect<Integer> background = new ComponentAspect<>("background", IFormat.INT, 0xFFFFFF);
	ComponentAspect<Integer> foreground = new ComponentAspect<>("foreground", IFormat.INT, 0xFFFFFF);
	
	ComponentAspect<Double> width = new ComponentAspect<>("width", IFormat.DOUBLE, 64D);
	ComponentAspect<Double> height = new ComponentAspect<>("height", IFormat.DOUBLE, 64D);
	
	@Override
	public PanelComponent build(AspectData data)
	{
		PanelComponent panel = new PanelComponent();
		
		panel.setBackground(new Color(data.getValue(background)));
		panel.setForeground(new Color(data.getValue(foreground)));
		panel.setSize(data.getValue(width), data.getValue(height));
		
		return panel;
	}
}
