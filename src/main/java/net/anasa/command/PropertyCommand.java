package net.anasa.command;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;

public class PropertyCommand implements ICommand
{
	private final String[] args;
	
	private final ICommandHandle handle;
	
	public PropertyCommand(String[] args, ICommandHandle handle)
	{
		this.args = args;
		
		this.handle = handle;
	}
	
	public String[] getArgs()
	{
		return args;
	}
	
	public ICommandHandle getHandle()
	{
		return handle;
	}
	
	@Override
	public boolean isValid(String data)
	{
		Listing<String> items = new Listing<>(data.isEmpty() ? new String[0] : data.split(" "));
		
		return items.size() == getArgs().length;
	}
	
	@Override
	public String run(String data)
	{
		Listing<String> items = new Listing<>(data.split(" "));
		
		Properties props = new Properties();
		
		props.set("data", StringHelper.join(data));
		
		for(int i = 0; i < getArgs().length; i++)
		{
			String arg = getArgs()[i];
			
			props.set(arg, items.get(i));
		}
		
		try
		{
			return getHandle().run(props);
		}
		catch(FormatException e)
		{
			return "Invalid arguments: " + data + " (" + e.getMessage() + ")";
		}
	}
	
	public interface ICommandHandle
	{
		public String run(Properties props) throws FormatException;
	}
}
