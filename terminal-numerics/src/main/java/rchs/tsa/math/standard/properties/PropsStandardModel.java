package rchs.tsa.math.standard.properties;

import rchs.tsa.math.standard.IStandardGradeLevel;
import rchs.tsa.math.standard.IStandardModel;
import net.anasa.util.Listing;
import net.anasa.util.data.properties.Properties;

public class PropsStandardModel extends PropsStandardNode implements IStandardModel
{
	public PropsStandardModel(Properties props)
	{
		super(props);
	}
	
	@Override
	public String getName()
	{
		return getProps().getValue();
	}
	
	@Override
	public Listing<IStandardGradeLevel> getGradeLevels()
	{
		return new Listing<>(getProps().getInnerProps().getValues()).conform((props) -> new PropsStandardGradeLevel(this, props));
	}
}
