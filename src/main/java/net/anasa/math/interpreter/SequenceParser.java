package net.anasa.math.interpreter;

import net.anasa.math.MathException;
import net.anasa.math.expression.ConstantType;
import net.anasa.math.expression.FunctionType;
import net.anasa.math.expression.IExpression;
import net.anasa.math.expression.OperatorType;
import net.anasa.math.interpreter.sequence.ExpressionResolver;
import net.anasa.math.sequence.SequenceToken.TokenType;
import net.anasa.util.Listing;
import net.anasa.util.NumberHelper;
import net.anasa.util.resolver.IToken;
import net.anasa.util.resolver.ResolverException;

public class SequenceParser implements IMathParser
{
	public static final SequenceParser EXPRESSION = new SequenceParser()
			.add(new RegexTokenBuilder("\\(", TokenType.OPEN_PARENTHESIS))
			.add(new RegexTokenBuilder("\\)", TokenType.CLOSE_PARENTHESIS))
			.add(new RegexTokenBuilder("=", TokenType.EQUALS))
			.add(new RegexTokenBuilder("<=", TokenType.LESS_THAN_EQUAL))
			.add(new RegexTokenBuilder(">=", TokenType.GREATER_THAN_EQUAL))
			.add(new RegexTokenBuilder("<", TokenType.LESS_THAN))
			.add(new RegexTokenBuilder(">", TokenType.GREATER_THAN))
			.add(new TokenBuilder(TokenType.FUNCTION, (data) -> {
				for(FunctionType type : FunctionType.values())
				{
					if(type.getName().startsWith(data.toLowerCase()))
					{
						return true;
					}
				}
				
				return false;
			}))
			.add(new TokenBuilder(TokenType.OPERATOR, (data) -> OperatorType.isOperator(data)))
			.add(new TokenBuilder(TokenType.NUMBER, (data) -> {
				for(ConstantType type : ConstantType.values())
				{
					if(type.getName().startsWith(data.toLowerCase()))
					{
						return true;
					}
				}
				
				return NumberHelper.isDouble(data);
			}))
			.add(new RegexTokenBuilder("[a-zA-Z_]*", TokenType.VARIABLE));
	
	private final Listing<ITokenBuilder> builders = new Listing<>();
	
	private SequenceParser()
	{
		
	}
	
	public Listing<ITokenBuilder> getBuilders()
	{
		return builders;
	}
	
	private SequenceParser add(ITokenBuilder builder)
	{
		getBuilders().add(builder);
		
		return this;
	}
	
	@Override
	public IExpression getFrom(String data) throws MathException
	{
		return getFrom(getSequence(data));
	}
	
	public IExpression getFrom(Listing<IToken> data) throws MathException
	{
		try
		{
			ExpressionResolver interpreter = new ExpressionResolver();
			return interpreter.resolve(data);
		}
		catch(ResolverException e)
		{
			throw new MathException(e);
		}
	}
	
	public Listing<IToken> getSequence(String data) throws MathException
	{
		Listing<IToken> tokens = new Listing<>();
		parseStep(data, tokens);
		
		return tokens;
	}
	
	private void parseStep(String data, Listing<IToken> tokens) throws MathException
	{
		if(data.isEmpty())
		{
			return;
		}
		
		for(ITokenBuilder builder : getBuilders())
		{
			for(int i = 1; i <= data.length(); i++)
			{
				if(i == data.length() || !builder.isValid(data.substring(0, i + 1)))
				{
					if(builder.isValid(data.substring(0, i)))
					{
						tokens.add(builder.getToken(data.substring(0, i)));
						parseStep(data.substring(i), tokens);
						return;
					}
					else
					{
						break;
					}
				}
			}
		}
		
		if(isEmptyChar(data.charAt(0)))
		{
			parseStep(data.substring(1), tokens);
		}
		else
		{
			throw new MathException("Could not determine token(s): " + data);
		}
	}
	
	private boolean isEmptyChar(char c)
	{
		return Character.isWhitespace(c);
	}
}
