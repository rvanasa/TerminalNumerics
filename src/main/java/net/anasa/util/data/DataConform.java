package net.anasa.util.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class DataConform
{
	public static <T, O> List<O> conform(T[] data, IConformHandler<T, O> handler) throws FormatException
	{
		return conform(Arrays.asList(data), handler);
	}
	
	public static <T, O> List<O> conform(Iterable<T> data, IConformHandler<T, O> handler) throws FormatException
	{
		List<O> list = new ArrayList<>(data instanceof Collection ? ((Collection<T>)data).size() : 10);
		
		for(T item : data)
		{
			list.add(handler.getFrom(item));
		}
		
		return list;
	}
	
	public interface IConformHandler<T, O>
	{
		public O getFrom(T data) throws FormatException;
	}
	
	public static class FormatException extends Exception
	{
		public FormatException(String message)
		{
			super(message);
		}
		
		public FormatException(Throwable e)
		{
			super(e);
		}
		
		public FormatException(String message, Throwable e)
		{
			super(message, e);
		}
	}
}
