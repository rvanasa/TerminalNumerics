package net.anasa.math.util;

import net.anasa.math.MathException;
import net.anasa.math.expression.IExpression;
import net.anasa.math.interpreter.SequenceParser;
import net.anasa.math.sequence.SequenceToken;
import net.anasa.util.Listing;

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
	
	public static IExpression evaluate(Listing<SequenceToken> data) throws MathException
	{
		return PARSER.getFrom(data);
	}
}
