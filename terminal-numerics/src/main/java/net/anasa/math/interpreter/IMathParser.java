package net.anasa.math.interpreter;

import net.anasa.math.MathException;
import net.anasa.math.expression.IExpression;

public interface IMathParser
{
	public IExpression getFrom(String data) throws MathException;
}
