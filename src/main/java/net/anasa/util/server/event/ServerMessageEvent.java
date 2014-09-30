package net.anasa.util.server.event;

import net.anasa.util.event.IEvent;
import net.anasa.util.server.Connection;

public class ServerMessageEvent implements IEvent
{
	private final Connection connection;
	
	private final String line;
	
	public ServerMessageEvent(Connection connection, String line)
	{
		this.connection = connection;
		
		this.line = line;
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public String getLine()
	{
		return line;
	}
}
