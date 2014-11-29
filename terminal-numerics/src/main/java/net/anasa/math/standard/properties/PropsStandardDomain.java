package net.anasa.math.standard.properties;

import net.anasa.math.standard.IStandard;
import net.anasa.math.standard.IStandardDomain;
import net.anasa.math.standard.IStandardGradeLevel;
import net.anasa.math.standard.Standard;
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
