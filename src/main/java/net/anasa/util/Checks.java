package net.anasa.util;

import java.util.Collection;

public final class Checks
{
	public static <E extends Throwable> void check(boolean expression, E e) throws E
	{
		if(!expression)
		{
			throw e;
		}
	}
	
	public static <T> T checkNotNull(T object) throws NullPointerException
	{
		return checkNotNull(object, new NullPointerException());
	}
	
	public static <T> T checkNotNull(T object, String message) throws NullPointerException
	{
		return checkNotNull(object, new NullPointerException(message));
	}
	
	public static <T, E extends Throwable> T checkNotNull(T object, E e) throws E
	{
		check(object != null, e);
		
		return object;
	}
	
	public static <T extends Collection<?>, E extends Throwable> T checkNotEmpty(T object, E e) throws E
	{
		check(object.size() > 0, e);
		
		return object;
	}
	
	public static <T, R, E extends Throwable> R checkInstanceOf(T object, Class<R> clazz, E e) throws E
	{
		checkNotNull(clazz, e);
		check(clazz.isInstance(object), e);
		
		return clazz.cast(object);
	}
}
