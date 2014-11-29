package rchs.tsa.math;

import net.anasa.util.StringHelper;

public class MathNumber
{
	private final double value;
	
	public MathNumber(double value)
	{
		this.value = value;
	}
	
	public double getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return StringHelper.stripZero(getValue());
	}

	public boolean isFinite()
	{
		return Double.isFinite(getValue());
	}
	
	public boolean isInfinite()
	{
		return Double.isInfinite(getValue());
	}
	
	public boolean isNaN()
	{
		return Double.isNaN(getValue());
	}

	public MathNumber negative()
	{
		return new MathNumber(-getValue());
	}
}
