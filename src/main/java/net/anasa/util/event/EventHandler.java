package net.anasa.util.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import net.anasa.util.Debug;
import net.anasa.util.event.EventDispatcher.IEventListener;

public final class EventHandler
{
	public static boolean dispatch(IEvent event, IEventListener listener)
	{
		if(listener == null)
		{
			Debug.warn("Event listener cannot be null");
			return false;
		}
		else if(event == null)
		{
			Debug.warn("Event type cannot be null");
			return false;
		}
		
		try
		{
			for(Method method : listener.getClass().getMethods())
			{
				if(method.isAnnotationPresent(EventListener.class))
				{
					if(method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(event.getClass()))
					{
						method.invoke(listener, event);
					}
				}
			}
		}
		catch(Exception e)
		{
			Debug.err("Event did not complete successfully: " + event);
			e.printStackTrace();
			listener.onFailEvent(event, e);
			return false;
		}
		
		return true;
	}
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	public @interface EventListener
	{
		
	}
}
