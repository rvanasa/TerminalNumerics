package rchs.tsa.math.interpreter.pattern;

import rchs.tsa.math.sequence.ExpressionTokenType;

public class RegexTokenPattern extends TokenPattern
{
	private final String regex;
	
	public RegexTokenPattern(String regex, ExpressionTokenType type)
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
