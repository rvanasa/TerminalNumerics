package net.anasa.math.interpreter;

import net.anasa.math.sequence.TokenType;

public class RegexTokenPattern extends TokenPattern
{
	private final String regex;
	
	public RegexTokenPattern(String regex, TokenType type)
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
