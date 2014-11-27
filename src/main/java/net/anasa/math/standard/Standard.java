package net.anasa.math.standard;

public class Standard implements IStandard
{
	private final IStandardDomain category;
	
	private final String id;
	
	private final String description;
	
	public Standard(IStandardDomain category, String id, String description)
	{
		this.category = category;
		
		this.id = id;
		
		this.description = description;
	}

	@Override
	public IStandardDomain getDomain()
	{
		return category;
	}

	@Override
	public String getID()
	{
		return id;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}
