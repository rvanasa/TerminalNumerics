package rchs.tsa.math.util;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IExpression;
import rchs.tsa.math.interpreter.SequenceParser;
import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;

public class Evaluator
{
	private static final SequenceParser PARSER = SequenceParser.EXPRESSION;
	
	public static IExpression evaluate(String data) throws MathException
	{
		return PARSER.getFrom(data);
	}
	
	public static IExpression evaluate(String data, IExpression def)
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
	
	public static IExpression evaluate(Listing<IToken> data) throws MathException
	{
		return PARSER.getFrom(data);
	}
}
