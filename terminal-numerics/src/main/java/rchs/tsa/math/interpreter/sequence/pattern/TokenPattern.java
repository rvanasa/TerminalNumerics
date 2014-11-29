package rchs.tsa.math.interpreter.sequence.pattern;

import rchs.tsa.math.sequence.TokenType;
import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.Token;

public class TokenPattern implements IParserPattern<IToken>
{
	private final TokenType type;
	private final ITokenMatcher matcher;
	
	public TokenPattern(TokenType type, ITokenMatcher matcher)
	{
		this.type = type;
		this.matcher = matcher;
	}
	
	public TokenType getType()
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
		return new Token(getType().name(), data);
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
