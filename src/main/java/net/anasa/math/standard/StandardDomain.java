package net.anasa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class StandardDomain implements IStandardDomain
{
	private final IStandardGrade level;
	
	private final String id;
	private final String description;
	
	private final Listing<IStandard> standards;
	
	public StandardDomain(IStandardGrade level, String id, String description, Listing<IStandard> standards)
	{
		this.level = level;
		
		this.id = id;
		this.description = description;
		
		this.standards = standards;
	}

	@Override
	public IStandardGrade getGrade()
	{
		return level;
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

	public Listing<IStandard> getStandards()
	{
		return standards;
	}

	@Override
	public IStandard getStandardByID(String id)
	{
		return getStandards().getFirst((standard) -> StringHelper.equalsIgnoreCase(standard.getID(), id));
	}
}