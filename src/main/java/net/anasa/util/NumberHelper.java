package net.anasa.util;

public final class NumberHelper
{
	public static int getInteger(String data)
	{
		return Integer.decode(data);
	}
	
	public static boolean isInteger(String data)
	{
		try
		{
			getInteger(data);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static double getDouble(String data)
	{
		return Double.parseDouble(data);
	}
	
	public static boolean isDouble(String data)
	{
		try
		{
			getDouble(data);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static byte getByte(String data)
	{
		return Byte.decode(data);
	}
	
	public static boolean isByte(String data)
	{
		try
		{
			getByte(data);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
