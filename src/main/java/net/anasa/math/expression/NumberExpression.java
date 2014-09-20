package net.anasa.math.expression;

import net.anasa.math.MathData;
import net.anasa.math.MathNumber;

public class NumberExpression extends MathExpression
{
	private final MathNumber number;
	
	public NumberExpression(MathNumber number)
	{
		this.number = number;
	}
	
	public MathNumber getNumber()
	{
		return number;
	}

	@Override
	public MathNumber evaluate(MathData data)
	{
		return getNumber();
	}

	@Override
	public String getStringValue()
	{
		return getNumber().toString();
	}
}
