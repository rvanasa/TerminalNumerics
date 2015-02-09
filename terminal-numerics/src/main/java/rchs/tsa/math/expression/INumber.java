package rchs.tsa.math.expression;

import net.anasa.util.StringHelper;

public interface INumber
{
	public INumber NaN = () -> Double.NaN;
	
	public double getValue();
	
	default String getStringValue()
	{
		return StringHelper.stripZero(getValue());
	}
}
