package net.anasa.math.standard;

import net.anasa.util.Listing;

public class StandardModel extends StandardNode implements IStandardModel
{
	private final String name;
	
	private final Listing<IStandardGradeLevel> gradeLevels;
	
	public StandardModel(String id, String name, Listing<IStandardGradeLevel> gradeLevels)
	{
		super(id);
		
		this.name = name;
		
		this.gradeLevels = gradeLevels;
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
}
