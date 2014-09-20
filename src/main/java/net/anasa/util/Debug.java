package net.anasa.util;

public final class Debug
{
	public static void log(Object message)
	{
		System.out.println(message);
	}
	
	public static void warn(Object message)
	{
		System.err.println("Warning: " + message);
	}
	
	public static void err(Object message)
	{
		System.err.println(message);
	}
}
