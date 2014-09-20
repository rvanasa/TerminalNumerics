package net.anasa.math.expression;

import net.anasa.math.MathData;
import net.anasa.math.MathException;
import net.anasa.math.MathNumber;
import net.anasa.util.Checks;

public class VariableExpression extends MathExpression
{
	private final String key;
	
	public VariableExpression(String key)
	{
		this.key = key;
	}

	public String getKey()
	{
		return key;
	}

	@Override
	public MathNumber evaluate(MathData data) throws MathException
	{
		Checks.check(data.hasVariable(getKey()), new MathException("Variable is not defined: " + getKey()));
		return data.getVariable(getKey());
	}

	@Override
	public String getStringValue()
	{
		return getKey();
	}
}
