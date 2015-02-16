package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public interface IFunction
{
	public String getName();
	
	public INumber evaluate(INumber number) throws MathException;
}
