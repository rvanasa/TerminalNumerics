package net.anasa.math.expression;

import net.anasa.math.MathData;
import net.anasa.math.MathException;
import net.anasa.math.MathNumber;

public interface IExpression
{
	public MathNumber evaluate(MathData data) throws MathException;
	
	public String getStringValue();
	
	public IExpression[] getChildren();
}
