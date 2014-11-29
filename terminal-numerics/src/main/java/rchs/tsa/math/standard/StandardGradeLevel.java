package rchs.tsa.math.standard;

import net.anasa.util.Listing;

public class StandardGradeLevel extends StandardNode implements IStandardGradeLevel
{
	private final IStandardModel model;
	
	private final String name;
	
	private final Listing<IStandardDomain> domains;
	
	public StandardGradeLevel(IStandardModel model, String id, String name, Listing<IStandardDomain> domains)
	{
		super(id);
		
		this.model = model;
		
		this.name = name;
		
		this.domains = domains;
	}
	
	@Override
	public IStandardModel getModel()
	{
		return model;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public Listing<IStandardDomain> getDomains()
	{
		return domains;
	}
}
