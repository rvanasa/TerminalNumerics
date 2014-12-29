package rchs.tsa.math.interpreter.pattern;

import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.Token;
import rchs.tsa.math.sequence.ExpressionTokenType;

public class TokenPattern implements IParserPattern<IToken>
{
	private final ExpressionTokenType type;
	private final ITokenMatcher matcher;
	
	public TokenPattern(ExpressionTokenType type, ITokenMatcher matcher)
	{
		this.type = type;
		this.matcher = matcher;
	}
	
	public ExpressionTokenType getType()
	{
		return type;
	}
	
	public ITokenMatcher getMatcher()
	{
		return matcher;
	}
	
	@Override
	public boolean isValid(String data)
	{
		return getMatcher().isValid(data);
	}
	
	@Override
	public IToken compile(String data)
	{
		return new Token(getType(), data);
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "::" + getType();
	}
	
	public interface ITokenMatcher
	{
		public boolean isValid(String data);
	}
}
