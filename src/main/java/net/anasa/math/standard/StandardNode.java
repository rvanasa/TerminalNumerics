package net.anasa.math.standard;

public abstract class StandardNode implements IStandardNode
{
	private final String id;
	
	public StandardNode(String id)
	{
		this.id = id.toUpperCase();
	}
	
	@Override
	public String getID()
	{
		return id;
	}
}
