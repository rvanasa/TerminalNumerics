package net.anasa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class StandardGradeLevel implements IStandardGradeLevel
{
	private final IStandardModel model;
	
	private final String id;
	private final String name;
	
	private final Listing<IStandardDomain> domains;
	
	public StandardGradeLevel(IStandardModel model, String id, String name, Listing<IStandardDomain> domains)
	{
		this.model = model;
		
		this.id = id;
		this.name = name;
		
		this.domains = domains;
	}
	
	@Override
	public IStandardModel getModel()
	{
		return model;
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
	public Listing<IStandardDomain> getDomains()
	{
		return domains;
	}
	
	@Override
	public IStandardDomain getDomainByID(String id)
	{
		return getDomains().getFirst((standard) -> StringHelper.equalsIgnoreCase(standard.getID(), id));
	}
}
