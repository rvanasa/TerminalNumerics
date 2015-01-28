package rchs.tsa.math.resource.module.internal.ui;

import java.awt.Color;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.UIComponent;
import rchs.tsa.math.resource.module.context.ComponentHandler;
import rchs.tsa.math.resource.module.context.IComponentHandler;

public abstract class UIComponentBuilder<T extends UIComponent<?>> extends ComponentBuilder<T>
{
	ComponentAspect<String> ref = new ComponentAspect<>("ref", IFormat.STRING, null);
	ComponentAspect<Integer> background = new ComponentAspect<>("background", IFormat.INT, 0xFFFFFF);
	ComponentAspect<Integer> foreground = new ComponentAspect<>("foreground", IFormat.INT, 0x000000);
	
	@Override
	public final IComponentHandler buildHandler(AspectData data)
	{
		T component = getComponent(data);
		
		component.setBackground(new Color(data.get(background)));
		component.setForeground(new Color(data.get(foreground)));
		
		return getHandler(data, component);
	}
	
	public abstract T getComponent(AspectData data);
	
	public IComponentHandler getHandler(AspectData data, T component)
	{
		ComponentHandler handler = new ComponentHandler(data.get(ref), component);
		setupHandler(data, handler, component);
		return handler;
	}
	
	public void setupHandler(AspectData data, ComponentHandler handler, T component)
	{
	}
}
