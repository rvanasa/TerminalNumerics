package net.anasa.command;

import net.anasa.util.Mapping;

public class CommandRegistry implements ICommand
{
	private final Mapping<String, ICommand> commands = new Mapping<>();
	
	public CommandRegistry()
	{
		
	}
	
	public Mapping<String, ICommand> getCommands()
	{
		return commands;
	}
	
	public void add(String name, ICommand command)
	{
		getCommands().put(name, command);
	}
	
	@Override
	public boolean isValid(String data)
	{
		ICommand command = getCommands().get(getName(data));
		return command != null && command.isValid(getArgs(data));
	}
	
	@Override
	public String run(String data)
	{
		if(isValid(data))
		{
			return getCommands().get(getName(data)).run(getArgs(data));
		}
		else
		{
			return "Invalid command syntax: " + data;
		}
	}
	
	private String getName(String data)
	{
		return data.contains(" ") ? data.substring(0, data.indexOf(" ")) : data;
	}
	
	private String getArgs(String data)
	{
		return data.contains(" ") ? data.substring(data.indexOf(" ") + 1) : "";
	}
}
