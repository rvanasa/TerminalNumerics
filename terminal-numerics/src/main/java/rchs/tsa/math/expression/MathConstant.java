package rchs.tsa.math.expression;

import net.anasa.util.Listing;

public class MathConstant implements IConstant
{
	private final Listing<String> names;
	private final MathNumber value;
	
	public MathConstant(double value, String... names)
	{
		this(new MathNumber(value), names);
	}
	
	public MathConstant(MathNumber value, String... names)
	{
		this.names = new Listing<>(names);
		this.value = value;
	}
	
	public Listing<String> getNames()
	{
		return names;
	}
	
	@Override
	public boolean isReferring(String name)
	{
		return getNames().contains(name);
	}
	
	@Override
	public INumber getValue()
	{
		return value;
	}
}
