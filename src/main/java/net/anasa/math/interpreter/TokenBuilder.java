package net.anasa.math.interpreter;

import net.anasa.math.sequence.SequenceToken;
import net.anasa.math.sequence.SequenceToken.TokenType;

public class TokenBuilder implements ITokenBuilder
{
	private final TokenType type;
	private final ITokenMatcher matcher;
	
	public TokenBuilder(TokenType type, ITokenMatcher matcher)
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
	public SequenceToken getToken(String data)
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
