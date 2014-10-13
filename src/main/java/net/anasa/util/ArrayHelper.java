package net.anasa.util;

public final class ArrayHelper
{
	public static <T> T getFromNullable(T[] data, int index)
	{
		if(index < 0 || index >= data.length)
		{
			return null;
		}
		
		return data[index];
	}
}
