package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public interface IOperator
{
	public char getSignature();
	
	public int getPriority();
	
	default boolean hasPriority(IOperator other)
	{
		return getPriority() > other.getPriority();
	}
	
	public INumber operate(INumber a, INumber b) throws MathException;
}
