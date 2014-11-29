package rchs.tsa.math.interpreter;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.ConstantType;
import rchs.tsa.math.expression.FunctionType;
import rchs.tsa.math.expression.IExpression;
import rchs.tsa.math.expression.OperatorType;
import rchs.tsa.math.interpreter.sequence.ExpressionResolver;
import rchs.tsa.math.interpreter.sequence.pattern.RegexTokenPattern;
import rchs.tsa.math.interpreter.sequence.pattern.TokenPattern;
import rchs.tsa.math.sequence.TokenType;
import net.anasa.util.Listing;
import net.anasa.util.NumberHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.data.parser.PatternParser;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;

public class SequenceParser extends PatternParser<IToken> implements IMathParser
{
	public static final SequenceParser EXPRESSION = new SequenceParser()
			.add(new RegexTokenPattern("\\(", TokenType.OPEN_PARENTHESIS))
			.add(new RegexTokenPattern("\\)", TokenType.CLOSE_PARENTHESIS))
			.add(new RegexTokenPattern("=", TokenType.EQUALS))
			.add(new RegexTokenPattern("<=", TokenType.LESS_THAN_EQUAL))
			.add(new RegexTokenPattern(">=", TokenType.GREATER_THAN_EQUAL))
			.add(new RegexTokenPattern("<", TokenType.LESS_THAN))
			.add(new RegexTokenPattern(">", TokenType.GREATER_THAN))
			.add(new TokenPattern(TokenType.FUNCTION, (data) -> new Listing<>(FunctionType.values()).checkAny((type) -> type.getName().equals(data.toLowerCase()))))
			.add(new TokenPattern(TokenType.OPERATOR, (data) -> OperatorType.isOperator(data)))
			.add(new TokenPattern(TokenType.NUMBER, (data) -> NumberHelper.isDouble(data) || new Listing<>(ConstantType.values()).checkAny((type) -> type.getName().equals(data.toLowerCase()))))
			.add(new RegexTokenPattern("[a-zA-Z_]*", TokenType.VARIABLE));
	
	public SequenceParser add(IParserPattern<IToken> pattern)
	{
		addPattern(pattern);
		
		return this;
	}
	
	@Override
	public IExpression getFrom(String data) throws MathException
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
	
	public IExpression getFrom(Listing<IToken> data) throws MathException
	{
		try
		{
			ExpressionResolver resolver = new ExpressionResolver();
			return resolver.resolve(data);
		}
		catch(ResolverException e)
		{
			throw new MathException(e);
		}
	}
}
