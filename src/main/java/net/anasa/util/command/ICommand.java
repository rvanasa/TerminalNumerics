package net.anasa.util.command;

public interface ICommand
{
	public boolean isValid(String data);
	
	public String run(String data);
}
