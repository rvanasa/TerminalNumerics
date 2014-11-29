package net.anasa.math.expression;

import net.anasa.math.MathData;
import net.anasa.math.MathNumber;

public enum OperatorType
{
	ADD(1, '+', (a, b, data) -> a + b),
	SUBTRACT(1, '-', (a, b, data) -> a - b),
	MULTIPLY(2, '*', (a, b, data) -> a * b),
	DIVIDE(2, '/', (a, b, data) -> a / b),
	REMAINDER(2, '%', (a, b, data) -> a % b),
	POWER(3, '^', (a, b, data) -> Math.pow(a, b));
	
	private final int priority;
	private final char signature;
	
	private final OperationTypeHandle handle;
	
	private OperatorType(int priority, char symbol, OperationTypeHandle handle)
	{
		this.priority = priority;
		this.signature = symbol;
		
		this.handle = handle;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public char getSignature()
	{
		return signature;
	}
	
	public boolean hasPriority(OperatorType other)
	{
		return getPriority() > other.getPriority();
	}

	public MathNumber getResult(MathNumber a, MathNumber b, MathData data)
	{
		return new MathNumber(handle.getResult(a.getValue(), b.getValue(), data));
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(getSignature());
	}
	
	public static boolean isOperator(String signature)
	{
		return get(signature) != null;
	}
	
	public static OperatorType get(String signature)
	{
		for(OperatorType op : values())
		{
			if(String.valueOf(op.getSignature()).equals(signature))
			{
				return op;
			}
		}
		
		return null;
	}
	
	private interface OperationTypeHandle
	{
		public double getResult(double a, double b, MathData data);
	}
}
