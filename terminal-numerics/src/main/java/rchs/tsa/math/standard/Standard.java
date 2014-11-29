package rchs.tsa.math.standard;

public class Standard extends StandardNode implements IStandard
{
	private final IStandardDomain category;
	
	private final String description;
	
	public Standard(IStandardDomain category, String id, String description)
	{
		super(id);
		
		this.category = category;
		
		this.description = description;
	}

	@Override
	public IStandardDomain getDomain()
	{
		return category;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}
