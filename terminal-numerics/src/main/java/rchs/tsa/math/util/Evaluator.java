package rchs.tsa.math.util;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.interpreter.SequenceParser;

public class Evaluator
{
	private static final SequenceParser PARSER = SequenceParser.EXPRESSION;
	
	public static IMathExpression evaluate(String data) throws MathException
	{
		return PARSER.getFrom(data);
	}
	
	public static IMathExpression evaluate(String data, IMathExpression def)
	{
		try
		{
			return evaluate(data);
		}
		catch(MathException e)
		{
			return def;
		}
	}
	
	public static IMathExpression evaluate(Listing<IToken> data) throws MathException
	{
		return PARSER.getFrom(data);
	}
}
