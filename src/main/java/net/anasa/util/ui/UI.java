package net.anasa.util.ui;

import net.anasa.util.Debug;

public final class UI
{
	public static void sendMessage(String message)
	{
		new MessageComponent("Message", message).display();
	}
	
	public static void sendError(String message)
	{
		Debug.err(message);
		
		new MessageComponent("Error Message", message).display();
	}
	
	public static void sendError(String message, Exception e)
	{
		e.printStackTrace();
		sendError(message + " (" + e + ")");
	}
}
