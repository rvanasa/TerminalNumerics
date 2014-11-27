package net.anasa.util;

import java.util.Collection;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public final class StringHelper
{
	public static String upperCase(String data)
	{
		return data == null ? null : data.toUpperCase();
	}
	
	public static String lowerCase(String data)
	{
		return data == null ? null : data.toLowerCase();
	}
	
	public static boolean equalsIgnoreCase(String a, String b)
	{
		return equals(lowerCase(a), lowerCase(b));
	}
	
	public static boolean equals(String a, String b)
	{
		return a == null ? b == null : a.equals(b);
	}
	
	public static boolean equals(char a, String b)
	{
		return b != null && !b.isEmpty() && a == b.charAt(0);
	}
	
	public static String join(String delimiter, Listing<?> values)
	{
		return join(delimiter, values.getValues());
	}
	
	public static String join(String delimiter, Collection<?> values)
	{
		return join(delimiter, values.toArray());
	}
	
	@SafeVarargs
	public static <T> String join(String delimiter, T... values)
	{
		String data = "";
		
		for(T value : values)
		{
			data = concat(delimiter, data, String.valueOf(value));
		}
		
		return data;
	}
	
	public static <T> String join(String delimiter, Iterable<T> values, IConformHandler<T, String> handler) throws FormatException
	{
		String data = "";
		
		for(T value : values)
		{
			data = concat(delimiter, data, handler.getFrom(value));
		}
		
		return data;
	}
	
	public static String concat(String delimiter, String original, String value)
	{
		if(!original.isEmpty())
		{
			original += delimiter;
		}
		
		return original + value;
	}
	
	public static String shear(String original, int left, int right)
	{
		return original.substring(left, original.length() - right);
	}
	
	public static String space(String data, int minLength)
	{
		for(int i = data.length(); i < minLength; i++)
		{
			data += " ";
		}
		
		return data;
	}
	
	public static String space(int length)
	{
		String data = "";
		for(int i = 0; i < length; i++)
		{
			data += " ";
		}
		
		return data;
	}
	
	public static String capitalize(String value)
	{
		if(value == null || value.isEmpty())
		{
			return value;
		}
		
		return String.join(" ", new Listing<String>(value.split(" "))
				.compute((word) -> word.substring(0, 1).toUpperCase() + word.substring(1))
				.toArray(String.class));
	}
	
	public static String getOrNull(Object object)
	{
		return object != null ? String.valueOf(object) : null;
	}
	
	public static String stripZero(double n)
	{
		int floor = (int)n;
		return String.valueOf(n == floor ? floor : n);
	}
	
	public static int getCorresponding(String data, int index, INestingSettings settings) throws NestingException
	{
		Checks.checkNotNull(data, new IllegalArgumentException("data cannot be null"));
		Checks.checkNotNull(settings, new IllegalArgumentException("settings cannot be null"));
		
		char open = data.charAt(index);
		char close = settings.getCorresponding(open);
		
		int indent = 0;
		
		for(int i = 0; i < data.length(); i++)
		{
			if((indent == 0 || open != close) && i == open)
			{
				indent++;
			}
			else if(i == close)
			{
				indent--;
			}
			
			if(indent == 0)
			{
				return i;
			}
		}
		
		throw new NestingException("No corresponding token was found for nesting pair: " + open + " " + close + "");
	}
	
	public static String getContained(String data, int index, INestingSettings settings) throws NestingException
	{
		Checks.checkNotNull(data, new IllegalArgumentException("data cannot be null"));
		return data.substring(index + 1, getCorresponding(data, index, settings));
	}
	
	public static String getBlock(String data, int index, INestingSettings settings) throws NestingException
	{
		Checks.checkNotNull(data, new IllegalArgumentException("data cannot be null"));
		return data.substring(index, getCorresponding(data, index, settings) + 1);
	}
	
	public static String stripNesting(String data, INestingSettings settings) throws NestingException
	{
		String ret = data;
		
		for(int i = 0; i < data.length(); i++)
		{
			if(settings.isOpeningCharacter(data.charAt(i)))
			{
				ret = ret.replace(getBlock(data, i, settings), "");
			}
		}
		
		return ret;
	}
	
	public static String s(double amount)
	{
		return amount == 1 ? "" : "s";
	}
	
	public interface INestingSettings
	{
		public Character getCorresponding(char open);
		
		public default boolean isOpeningCharacter(char c)
		{
			return getCorresponding(c) != null;
		}
	}
	
	public static class NestingException extends Exception
	{
		public NestingException(String message)
		{
			super(message);
		}
	}
}
