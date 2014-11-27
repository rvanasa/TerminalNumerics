package net.anasa.math.module.provided.ui;

import java.awt.Color;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.UIComponent;

public abstract class CommonComponentBuilder extends ComponentBuilder<UIComponent<?>>
{
	ComponentAspect<Integer> background = new ComponentAspect<>("background", IFormat.INT, 0xFFFFFF);
	ComponentAspect<Integer> foreground = new ComponentAspect<>("foreground", IFormat.INT, 0xFFFFFF);
	
	ComponentAspect<Double> width = new ComponentAspect<>("width", IFormat.DOUBLE, 64D);
	ComponentAspect<Double> height = new ComponentAspect<>("height", IFormat.DOUBLE, 64D);
	
	@Override
	public final UIComponent<?> build(AspectData data)
	{
		UIComponent<?> component = getComponent(data);
		
		component.setBackground(new Color(data.getValue(background)));
		component.setForeground(new Color(data.getValue(foreground)));
		component.setSize(data.getValue(width), data.getValue(height));
		
		return component;
	}
	
	public abstract UIComponent<?> getComponent(AspectData data);
}
