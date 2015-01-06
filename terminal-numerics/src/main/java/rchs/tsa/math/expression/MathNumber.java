package rchs.tsa.math.expression;

import net.anasa.util.StringHelper;

public class MathNumber implements INumber
{
	public static final MathNumber NaN = new MathNumber(Double.NaN);
	
	private final double value;
	
	public MathNumber(double value)
	{
		this.value = value;
	}
	
	@Override
	public double getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return StringHelper.stripZero(getValue());
	}
	
	@Override
	public String getStringValue()
	{
		return toString();
	}
}
