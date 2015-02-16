package rchs.tsa.math.interpreter;

import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.data.parser.PatternParser;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.interpreter.pattern.RegexTokenPattern;
import rchs.tsa.math.interpreter.pattern.TokenPattern;
import rchs.tsa.math.interpreter.sequence.ExpressionResolver;
import rchs.tsa.math.sequence.ExpressionTokenType;

public class SequenceParser extends PatternParser<IToken> implements IMathParser
{
	private final MathData mathData;
	
	private final ExpressionResolver resolver;
	
	public SequenceParser(MathData mathData)
	{
		this.mathData = mathData;
		
		this.resolver = new ExpressionResolver(mathData);
		
		addPattern(new RegexTokenPattern("\\(", ExpressionTokenType.OPEN_PARENTHESIS));
		addPattern(new RegexTokenPattern("\\)", ExpressionTokenType.CLOSE_PARENTHESIS));
		addPattern(new RegexTokenPattern("=", ExpressionTokenType.EQUALS));
		addPattern(new RegexTokenPattern("<=", ExpressionTokenType.LESS_THAN_EQUAL));
		addPattern(new RegexTokenPattern(">=", ExpressionTokenType.GREATER_THAN_EQUAL));
		addPattern(new RegexTokenPattern("<", ExpressionTokenType.LESS_THAN));
		addPattern(new RegexTokenPattern(">", ExpressionTokenType.GREATER_THAN));
		addPattern(new TokenPattern(ExpressionTokenType.FUNCTION, (data) -> mathData.isFunction(data)));
		addPattern(new TokenPattern(ExpressionTokenType.OPERATOR, (data) -> mathData.isOperator(data)));
		addPattern(new TokenPattern(ExpressionTokenType.NUMBER, (data) -> mathData.isNumber(data)));
		addPattern(new RegexTokenPattern("'[^']+?'", ExpressionTokenType.VARIABLE, (data) -> data.substring(1, data.length() - 1)));
		addPattern(new RegexTokenPattern("[a-zA-Z]", ExpressionTokenType.VARIABLE));
	}
	
	public MathData getMathData()
	{
		return mathData;
	}
	
	public ExpressionResolver getResolver()
	{
		return resolver;
	}
	
	public SequenceParser add(IParserPattern<IToken> pattern)
	{
		addPattern(pattern);
		
		return this;
	}
	
	@Override
	public IMathExpression getFrom(String data) throws MathException
	{
		try
		{
			return getFrom(parse(data));
		}
		catch(FormatException e)
		{
			throw new MathException(e);
		}
	}
	
	public IMathExpression getFrom(Listing<IToken> data) throws MathException
	{
		try
		{
			return getResolver().resolve(data);
		}
		catch(ResolverException e)
		{
			throw new MathException(e);
		}
	}
}
