package net.anasa.math.expression;

public abstract class MathExpression implements IExpression
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
