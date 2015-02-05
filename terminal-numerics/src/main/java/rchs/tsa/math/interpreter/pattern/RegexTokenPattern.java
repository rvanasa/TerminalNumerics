package rchs.tsa.math.interpreter.pattern;

import java.util.function.Function;

import rchs.tsa.math.sequence.ExpressionTokenType;

public class RegexTokenPattern extends TokenPattern
{
	private final String regex;
	
	public RegexTokenPattern(String regex, ExpressionTokenType type)
	{
		this(regex, type, (s) -> s);
	}
	
	public RegexTokenPattern(String regex, ExpressionTokenType type, Function<String, String> builder)
	{
		super(type, (data) -> data.matches(regex), builder);
		
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
