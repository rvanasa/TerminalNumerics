package net.anasa.math.util;

import net.anasa.util.Debug;
import net.anasa.util.ui.MessageComponent;

public final class UI
{
	public static void sendError(String message)
	{
		Debug.err(message);
		
		MessageComponent ui = new MessageComponent("Error", message);
		ui.display();
	}
	
	public static void sendError(String message, Exception e)
	{
		e.printStackTrace();
		sendError(message + " (" + e + "");
	}
}
