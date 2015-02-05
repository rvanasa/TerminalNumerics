package rchs.tsa.math.interpreter;

import net.anasa.util.Listing;
import net.anasa.util.NumberHelper;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.data.parser.PatternParser;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.ConstantType;
import rchs.tsa.math.expression.FunctionType;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.OperatorType;
import rchs.tsa.math.interpreter.pattern.RegexTokenPattern;
import rchs.tsa.math.interpreter.pattern.TokenPattern;
import rchs.tsa.math.interpreter.sequence.ExpressionResolver;
import rchs.tsa.math.sequence.ExpressionTokenType;

public class SequenceParser extends PatternParser<IToken> implements IMathParser
{
	public static final SequenceParser EXPRESSION = new SequenceParser()
			.add(new RegexTokenPattern("\\(", ExpressionTokenType.OPEN_PARENTHESIS))
			.add(new RegexTokenPattern("\\)", ExpressionTokenType.CLOSE_PARENTHESIS))
			.add(new RegexTokenPattern("=", ExpressionTokenType.EQUALS))
			.add(new RegexTokenPattern("<=", ExpressionTokenType.LESS_THAN_EQUAL))
			.add(new RegexTokenPattern(">=", ExpressionTokenType.GREATER_THAN_EQUAL))
			.add(new RegexTokenPattern("<", ExpressionTokenType.LESS_THAN))
			.add(new RegexTokenPattern(">", ExpressionTokenType.GREATER_THAN))
			.add(new TokenPattern(ExpressionTokenType.FUNCTION, (data) -> FunctionType.isFunction(data)))
			.add(new TokenPattern(ExpressionTokenType.OPERATOR, (data) -> OperatorType.isOperator(data)))
			.add(new TokenPattern(ExpressionTokenType.NUMBER, (data) -> NumberHelper.isDouble(data) || ConstantType.isConstant(data)))
			.add(new RegexTokenPattern("['].*[']", ExpressionTokenType.VARIABLE, (data) -> data.substring(1, data.length() - 1)))
			.add(new RegexTokenPattern("[a-zA-Z]", ExpressionTokenType.VARIABLE));
	
	private final ExpressionResolver resolver = new ExpressionResolver();
	
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
