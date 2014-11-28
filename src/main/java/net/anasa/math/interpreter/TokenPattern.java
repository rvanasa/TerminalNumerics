package net.anasa.math.interpreter;

import net.anasa.math.sequence.SequenceToken;
import net.anasa.math.sequence.SequenceToken.TokenType;
import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.resolver.IToken;

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
		return new SequenceToken(getType(), data);
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
