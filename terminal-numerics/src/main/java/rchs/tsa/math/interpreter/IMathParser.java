package rchs.tsa.math.interpreter;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;

public interface IMathParser
{
	public IMathExpression getFrom(String data) throws MathException;
}
