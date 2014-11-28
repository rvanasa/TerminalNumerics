package net.anasa.math.interpreter;

import net.anasa.math.MathException;
import net.anasa.math.expression.ConstantType;
import net.anasa.math.expression.FunctionType;
import net.anasa.math.expression.IExpression;
import net.anasa.math.expression.OperatorType;
import net.anasa.math.interpreter.sequence.ExpressionResolver;
import net.anasa.math.sequence.TokenType;
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
