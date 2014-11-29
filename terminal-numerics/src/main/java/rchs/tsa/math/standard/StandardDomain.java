package rchs.tsa.math.standard;

import net.anasa.util.Listing;

public class StandardDomain extends StandardNode implements IStandardDomain
{
	private final IStandardGradeLevel level;
	
	private final String description;
	
	private final Listing<IStandard> standards;
	
	public StandardDomain(IStandardGradeLevel level, String id, String description, Listing<IStandard> standards)
	{
		super(id);
		
		this.level = level;
		
		this.description = description;
		
		this.standards = standards;
	}
	
	@Override
	public IStandardGradeLevel getGrade()
	{
		return level;
	}
	
	@Override
	public String getDescription()
	{
		return description;
	}
	
	@Override
	public Listing<IStandard> getStandards()
	{
		return standards;
	}
}
