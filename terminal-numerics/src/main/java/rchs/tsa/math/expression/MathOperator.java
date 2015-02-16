package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public class MathOperator implements IOperator
{
	private final char signature;
	private final int priority;
	
	private final IMathOperatorHandle operator;
	
	public MathOperator(char signature, int priority, IMathOperatorHandle operator)
	{
		this.signature = signature;
		this.priority = priority;
		this.operator = operator;
	}
	
	@Override
	public char getSignature()
	{
		return signature;
	}
	
	@Override
	public int getPriority()
	{
		return priority;
	}
	
	public IMathOperatorHandle getHandle()
	{
		return operator;
	}
	
	@Override
	public INumber operate(INumber a, INumber b) throws MathException
	{
		return new MathNumber(getHandle().operate(a.getValue(), b.getValue()));
	}
	
	public interface IMathOperatorHandle
	{
		public double operate(double a, double b) throws MathException;
	}
}
