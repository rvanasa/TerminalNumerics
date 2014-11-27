package net.anasa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class StandardModel implements IStandardModel
{
	private final String id;
	private final String name;
	
	private final Listing<IStandardGradeLevel> gradeLevels;
	
	public StandardModel(String id, String name, Listing<IStandardGradeLevel> gradeLevels)
	{
		this.id = id;
		this.name = name;
		
		this.gradeLevels = gradeLevels;
	}
	
	@Override
	public String getID()
	{
		return id;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public Listing<IStandardGradeLevel> getGradeLevels()
	{
		return gradeLevels;
	}
	
	@Override
	public IStandardGradeLevel getGradeByID(String id)
	{
		return getGradeLevels().getFirst((category) -> StringHelper.equalsIgnoreCase(category.getID(), id));
	}
}
