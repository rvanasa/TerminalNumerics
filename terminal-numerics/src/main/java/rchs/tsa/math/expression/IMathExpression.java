package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public interface IMathExpression
{
	public INumber evaluate(MathData data) throws MathException;
	
	public String getStringValue();
	
	public IMathExpression[] getChildren();
}
