package net.anasa.util.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.event.EventDispatcher;
import net.anasa.util.event.EventDispatcher.IEventListener;
import net.anasa.util.event.EventHandler.EventListener;
import net.anasa.util.server.event.ServerMessageEvent;

public class Server implements IEventListener
{
	private final ServerSocket socket;
	
	private final Listing<Connection> connections = new Listing<Connection>().setHandle(new CopyOnWriteArrayList<>());
	
	private final EventDispatcher events = new EventDispatcher();
	
	public Server(int port) throws IOException
	{
		this.socket = new ServerSocket(port);
		
		Debug.log("Server started on port " + getPort());
		
		new Thread(() -> {
			while(isAlive())
			{
				getConnections().forEach((connection) -> {
					if(!connection.isAlive())
					{
						disconnect(connection);
					}
				});
				
				try
				{
					connect(new Connection(getSocket().accept()));
				}
				catch(IOException e)
				{
					Debug.log("Error occurred on server connection listener thread (" + e + ")");
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public ServerSocket getSocket()
	{
		return socket;
	}
	
	public Listing<Connection> getConnections()
	{
		return connections;
	}
	
	public void connect(Connection connection)
	{
		getConnections().add(connection);
		connection.addEventListener(this);
		
		Debug.log(connection + " connected successfully");
	}
	
	public void disconnect(Connection connection)
	{
		if(getConnections().contains(connection))
		{
			getConnections().remove(connection);
			Debug.log(connection + " disconnected");
		}
	}
	
	public EventDispatcher getEventDispatcher()
	{
		return events;
	}
	
	public void addEventListener(IEventListener listener)
	{
		getEventDispatcher().register(listener);
	}
	
	public int getPort()
	{
		return getSocket().getLocalPort();
	}
	
	@Override
	public boolean isAlive()
	{
		return getSocket().isBound() && !getSocket().isClosed();
	}
	
	public void sendMessage(String message)
	{
		for(Connection connection : getConnections())
		{
			try
			{
				connection.sendMessage(message);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@EventListener
	public void onMessage(ServerMessageEvent event)
	{
		Debug.log("[" + event.getConnection().getAddress() + "] " + event.getLine());
		getEventDispatcher().dispatch(event);
	}
}
