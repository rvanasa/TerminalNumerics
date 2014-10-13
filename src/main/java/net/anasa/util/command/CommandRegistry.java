package net.anasa.util.command;

import java.util.regex.Pattern;

import net.anasa.util.Mapping;

public class CommandRegistry implements ICommand
{
	private final Mapping<String, ICommand> commands = new Mapping<>();
	
	public Mapping<String, ICommand> getCommands()
	{
		return commands;
	}
	
	public void add(String name, ICommand command)
	{
		for(String parse : name.split(Pattern.quote("|")))
		{
			getCommands().put(parse, command);
		}
	}
	
	@Override
	public boolean isValid(String data)
	{
		return getCommand(data) != null;
	}
	
	@Override
	public String run(String data)
	{
		if(isValid(data))
		{
			return getCommand(data).run(getArgs(data));
		}
		else
		{
			return "Invalid command: " + data;
		}
	}
	
	public ICommand getCommand(String data)
	{
		String name = getName(data);
		ICommand command = getCommands().getFirst((key, value) -> key.matches(name));
		
		return (command != null && command.isValid(getArgs(data))) ? command : null;
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
