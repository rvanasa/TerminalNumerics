package net.anasa.util;

import java.io.PrintStream;

public final class Debug
{
	private static final Listing<IDebugListener> LISTENERS = new Listing<>();
	
	static
	{
		System.setOut(new PrintStream(System.out)
		{
			@Override
			public void print(String message)
			{
				super.print(message);
				msg(message, MessageType.INFO);
			}
		});
		
		System.setErr(new PrintStream(System.err)
		{
			@Override
			public void print(String message)
			{
				super.print(message);
				msg(message, MessageType.ERROR);
			}
		});
	}
	
	public static void registerListener(IDebugListener listener)
	{
		if(listener != null)
		{
			LISTENERS.add(listener);
		}
	}
	
	private static void msg(Object message, MessageType type)
	{
		for(IDebugListener listener : LISTENERS)
		{
			listener.onMessage(String.valueOf(message), type);
		}
	}
	
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
	
	public interface IDebugListener
	{
		public void onMessage(String message, MessageType type);
	}
	
	public enum MessageType
	{
		INFO,
		ERROR;
	}
}
