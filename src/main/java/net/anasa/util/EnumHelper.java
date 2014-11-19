package net.anasa.util;

import java.lang.reflect.Method;

import net.anasa.util.data.DataConform.FormatException;

public final class EnumHelper
{
	public static <T> T getFrom(Class<T> clazz, String name) throws FormatException
	{
		if(name == null)
		{
			return null;
		}
		else if(!clazz.isEnum())
		{
			throw new FormatException(clazz.getSimpleName() + " must be an enum");
		}
		
		try
		{
			Method nameMethod = clazz.getMethod("name");
			
			T[] data = clazz.getEnumConstants();
			
			for(T type : data)
			{
				if(StringHelper.equalsIgnoreCase(name, (String)nameMethod.invoke(type)))
				{
					return type;
				}
			}
		}
		catch(ReflectiveOperationException e)
		{
			throw new FormatException("Failed to determine enum values", e);
		}
		
		throw new FormatException("Invalid " + clazz.getSimpleName() + ": " + name);
	}
	
	public static <T> T getFrom(Class<T> clazz, String name, T def)
	{
		try
		{
			T value = getFrom(clazz, name);
			
			return value != null ? value : def;
		}
		catch(FormatException e)
		{
			return def;
		}
	}
}
