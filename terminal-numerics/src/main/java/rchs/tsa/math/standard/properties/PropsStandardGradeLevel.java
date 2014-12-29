package rchs.tsa.math.standard.properties;

import net.anasa.util.Listing;
import net.anasa.util.data.properties.Properties;
import rchs.tsa.math.standard.IStandardDomain;
import rchs.tsa.math.standard.IStandardGradeLevel;
import rchs.tsa.math.standard.IStandardModel;

public class PropsStandardGradeLevel extends PropsStandardNode implements IStandardGradeLevel
{
	private final IStandardModel model;
	
	public PropsStandardGradeLevel(IStandardModel model, Properties props)
	{
		super(props);
		
		this.model = model;
	}

	@Override
	public IStandardModel getModel()
	{
		return model;
	}
	
	@Override
	public String getName()
	{
		return getProps().getValue();
	}
	
	@Override
	public Listing<IStandardDomain> getDomains()
	{
		return new Listing<>(getProps().getInnerProps().getValues()).format((props) -> new PropsStandardDomain(this, props));
	}
}
