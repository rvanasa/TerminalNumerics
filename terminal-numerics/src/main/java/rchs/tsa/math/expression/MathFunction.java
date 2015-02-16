package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public class MathFunction implements IFunction
{
	private final String name;
	private final IMathFunctionHandle function;
	
	public MathFunction(String name, IMathFunctionHandle function)
	{
		this.name = name;
		this.function = function;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public IMathFunctionHandle getHandle()
	{
		return function;
	}
	
	@Override
	public INumber evaluate(INumber number) throws MathException
	{
		return new MathNumber(getHandle().evaluate(number.getValue()));
	}
	
	public interface IMathFunctionHandle
	{
		public double evaluate(double number) throws MathException;
	}
}
