package rchs.tsa.math.expression;

import rchs.tsa.math.MathData;
import rchs.tsa.math.MathException;
import rchs.tsa.math.MathNumber;

public interface IExpression
{
	public MathNumber evaluate(MathData data) throws MathException;
	
	public String getStringValue();
	
	public IExpression[] getChildren();
}
