package net.anasa.math.interpreter;

import net.anasa.math.sequence.SequenceToken.TokenType;

public class RegexTokenBuilder extends TokenBuilder
{
	private final String regex;
	
	public RegexTokenBuilder(String regex, TokenType type)
	{
		super(type, (data) -> data.matches(regex));
		
		this.regex = regex;
	}
	
	public String getRegex()
	{
		return regex;
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "::[" + getRegex() + "] " + getType();
	}
}
