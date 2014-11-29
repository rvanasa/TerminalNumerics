package rchs.tsa.math.interpreter;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IExpression;

public interface IMathParser
{
	public IExpression getFrom(String data) throws MathException;
}
