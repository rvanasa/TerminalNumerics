package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public interface IExpression
{
	public INumber evaluate(MathData data) throws MathException;
	
	public String getStringValue();
	
	public IExpression[] getChildren();
}
