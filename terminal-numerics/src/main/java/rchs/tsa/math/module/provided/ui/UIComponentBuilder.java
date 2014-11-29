package rchs.tsa.math.module.provided.ui;

import java.awt.Color;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.UIComponent;

public abstract class UIComponentBuilder extends ComponentBuilder<UIComponent<?>>
{
	ComponentAspect<Integer> background = new ComponentAspect<>("background", IFormat.INT, 0xFFFFFF);
	ComponentAspect<Integer> foreground = new ComponentAspect<>("foreground", IFormat.INT, 0x000000);
	
	@Override
	public final UIComponent<?> build(AspectData data)
	{
		UIComponent<?> component = getComponent(data);
		
		component.setBackground(new Color(data.getValue(background)));
		component.setForeground(new Color(data.getValue(foreground)));
		
		return component;
	}
	
	public abstract UIComponent<?> getComponent(AspectData data);
}
