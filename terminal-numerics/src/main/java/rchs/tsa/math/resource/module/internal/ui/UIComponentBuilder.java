package rchs.tsa.math.resource.module.internal.ui;

import java.awt.Color;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.UIComponent;

public abstract class UIComponentBuilder extends ComponentBuilder<UIComponent<?>>
{
	ComponentAspect<String> ref = new ComponentAspect<>("ref", IFormat.STRING, null);
	ComponentAspect<Integer> background = new ComponentAspect<>("background", IFormat.INT, 0xFFFFFF);
	ComponentAspect<Integer> foreground = new ComponentAspect<>("foreground", IFormat.INT, 0x000000);
	
	@Override
	public final UIComponent<?> buildComponent(AspectData data)
	{
		UIComponent<?> component = getComponent(data);
		
		component.setBackground(new Color(data.get(background)));
		component.setForeground(new Color(data.get(foreground)));
		
		return component;
	}
	
	public abstract UIComponent<?> getComponent(AspectData data);
}
