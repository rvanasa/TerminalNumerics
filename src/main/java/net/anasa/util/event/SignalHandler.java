package net.anasa.util.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import net.anasa.util.Debug;

public final class SignalHandler
{
	public static boolean sendSignal(String signal, ISignalListener listener)
	{
		if(listener == null)
		{
			Debug.warn("Signal listener cannot be null");
			return false;
		}
		else if(signal == null)
		{
			Debug.warn("Signal cannot be null");
			return false;
		}
		
		try
		{
			for(Method method : listener.getClass().getMethods())
			{
				if(method.isAnnotationPresent(SignalListener.class) && method.getAnnotation(SignalListener.class).value().equalsIgnoreCase(signal))
				{
					if(method.getParameterTypes().length == 0)
					{
						method.invoke(listener);
					}
					else
					{
						throw new IllegalArgumentException("Signal listener methods can not have parameters");
					}
				}
			}
		}
		catch(Exception e)
		{
			Debug.err("Signal did not complete successfully: " + signal);
			e.printStackTrace();
			listener.onFailSignal(signal, e);
			return false;
		}
		
		return true;
	}
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	public @interface SignalListener
	{
		public String value();
	}
	
	public interface ISignalListener
	{
		public void onFailSignal(String signal, Throwable e);
	}
}
