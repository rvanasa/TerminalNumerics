package rchs.tsa.math.expression;

public abstract class MathExpression implements IMathExpression
{
	public MathExpression()
	{
		
	}
	
	@Override
	public String toString()
	{
		return getStringValue();
	}
}
