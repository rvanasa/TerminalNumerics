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
				msg(MessageType.INFO, message);
			}
		});
		
		System.setErr(new PrintStream(System.err)
		{
			@Override
			public void print(String message)
			{
				super.print(message);
				msg(MessageType.ERROR, message);
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
	
	private static void msg(MessageType type, Object message)
	{
		for(IDebugListener listener : LISTENERS)
		{
			listener.onMessage(type, String.valueOf(message));
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
		public void onMessage(MessageType type, String message);
	}
	
	public enum MessageType
	{
		INFO,
		ERROR;
	}
}
