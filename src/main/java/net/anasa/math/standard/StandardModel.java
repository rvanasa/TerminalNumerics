package net.anasa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class StandardModel implements IStandardModel
{
	private final String id;
	private final String type;
	
	private final Listing<IStandardGrade> gradeLevels;
	
	public StandardModel(String id, String type, Listing<IStandardGrade> gradeLevels)
	{
		this.id = id;
		this.type = type;
		
		this.gradeLevels = gradeLevels;
	}
	
	@Override
	public String getID()
	{
		return id;
	}

	@Override
	public String getType()
	{
		return type;
	}

	public Listing<IStandardGrade> getGradeLevels()
	{
		return gradeLevels;
	}

	@Override
	public IStandardGrade getGradeByID(String id)
	{
		return getGradeLevels().getFirst((category) -> StringHelper.equalsIgnoreCase(category.getID(), id));
	}
}
