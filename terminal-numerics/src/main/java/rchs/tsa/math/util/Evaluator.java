package rchs.tsa.math.util;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.interpreter.SequenceParser;

public class Evaluator
{
	public static SequenceParser getParser(MathData mathData)
	{
		return new SequenceParser(mathData);
	}
	
	public static IMathExpression parse(MathData mathData, String data) throws MathException
	{
		return getParser(mathData).getFrom(data);
	}
	
	public static IMathExpression parse(MathData mathData, String data, IMathExpression def)
	{
		try
		{
			return parse(mathData, data);
		}
		catch(MathException e)
		{
			return def;
		}
	}
	
	public static IMathExpression parse(MathData mathData, Listing<IToken> data) throws MathException
	{
		return getParser(mathData).getFrom(data);
	}
}
