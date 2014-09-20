package net.anasa.util.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.anasa.util.data.DataConform.FormatException;

public final class DataFormatHelper
{
	public static <T> T getObject(Class<T> clazz, String data) throws FormatException
	{
		try
		{
			for(Method method : clazz.getMethods())
			{
				if(Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(DataFormat.class) && method.getParameterTypes().equals(new Class[] {String.class}))
				{
					if(clazz.isAssignableFrom(method.getReturnType()))
					{
						return clazz.cast(method.invoke(null, data));
					}
					else
					{
						throw new FormatException("Invalid return type for format handler method: " + method);
					}
				}
			}
			
			throw new FormatException("Could not find applicable data format handler method for class: " + clazz);
		}
		catch(FormatException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new FormatException(e);
		}
	}
	
	public static <T> String getFormatted(Class<T> clazz, T data) throws FormatException
	{
		try
		{
			for(Method method : clazz.getMethods())
			{
				if(Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(DataFormat.class) && method.getParameterTypes().equals(new Class[] {clazz}))
				{
					if(String.class.isAssignableFrom(method.getReturnType()))
					{
						return String.valueOf(method.invoke(null, data));
					}
					else
					{
						throw new FormatException("Invalid return type for format handler method: " + method);
					}
				}
			}
			
			throw new FormatException("Could not find applicable data format handler method for class: " + clazz);
		}
		catch(FormatException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new FormatException(e);
		}
	}
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DataFormat
	{
		
	}
}
