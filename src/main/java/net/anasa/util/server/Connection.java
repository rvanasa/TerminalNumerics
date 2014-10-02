package net.anasa.util.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import net.anasa.util.Debug;
import net.anasa.util.event.EventDispatcher;
import net.anasa.util.event.EventDispatcher.IEventListener;
import net.anasa.util.server.event.ServerMessageEvent;

public class Connection
{
	private final EventDispatcher events = new EventDispatcher();
	
	private final Socket socket;
	
	private final BufferedReader input;
	private final BufferedWriter output;
	
	public Connection(String address, int port) throws UnknownHostException, IOException
	{
		this(new Socket(address, port));
	}
	
	public Connection(Socket socket) throws IOException
	{
		this.socket = socket;
		
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		new Thread(() -> {
			while(isAlive())
			{
				try
				{
					if(getInput().ready())
					{
						String line;
						while((line = getInput().readLine()) != null)
						{
							getEventDispatcher().dispatch(new ServerMessageEvent(this, line));
						}
					}
				}
				catch(IOException e)
				{
					if(isAlive())
					{
						Debug.err("Error occurred in connection thread (" + this + ")");
						e.printStackTrace();
					}
					else
					{
						Debug.log(e.getMessage());
					}
				}
			}
		}).start();
	}
	
	public EventDispatcher getEventDispatcher()
	{
		return events;
	}
	
	public void addEventListener(IEventListener listener)
	{
		getEventDispatcher().register(listener);
	}
	
	protected Socket getSocket()
	{
		return socket;
	}
	
	public BufferedReader getInput()
	{
		return input;
	}
	
	public BufferedWriter getOutput()
	{
		return output;
	}
	
	public String getAddress()
	{
		return getSocket().getInetAddress().getHostAddress();
	}
	
	public int getPort()
	{
		return getSocket().getPort();
	}
	
	public void close() throws IOException
	{
		getInput().close();
		getOutput().close();
		
		getSocket().close();
	}
	
	public boolean isAlive()
	{
		return getSocket().isConnected() && !getSocket().isClosed();
	}
	
	public void sendMessage(String message) throws IOException
	{
		getOutput().write(message);
		
		if(message == null || !message.endsWith("\n"))
		{
			getOutput().newLine();
		}
		
		getOutput().flush();
	}
	
	@Override
	public String toString()
	{
		return getAddress() + ":" + getPort();
	}
}
