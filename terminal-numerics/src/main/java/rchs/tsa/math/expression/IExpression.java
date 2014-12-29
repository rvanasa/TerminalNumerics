package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public interface IExpression
{
	public MathNumber evaluate(MathData data) throws MathException;
	
	public String getStringValue();
	
	public IExpression[] getChildren();
}
