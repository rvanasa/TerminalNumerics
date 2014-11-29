package rchs.tsa.math.standard.properties;

import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardDomain;
import rchs.tsa.math.standard.IStandardGradeLevel;
import rchs.tsa.math.standard.Standard;
import net.anasa.util.Listing;
import net.anasa.util.data.properties.Properties;

public class PropsStandardDomain extends PropsStandardNode implements IStandardDomain
{
	private final IStandardGradeLevel grade;
	
	public PropsStandardDomain(IStandardGradeLevel grade, Properties props)
	{
		super(props);
		
		this.grade = grade;
	}

	@Override
	public IStandardGradeLevel getGrade()
	{
		return grade;
	}

	@Override
	public String getDescription()
	{
		return getProps().getValue();
	}

	@Override
	public Listing<IStandard> getStandards()
	{
		return new Listing<>(getProps().getKeys()).conform((key) -> new Standard(this, key, getProps().getString(key)));
	}
}
