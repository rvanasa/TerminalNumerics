package rchs.tsa.math.interpreter.pattern;

import java.util.function.Function;
import java.util.function.Predicate;

import net.anasa.util.data.parser.IParserPattern;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.Token;
import rchs.tsa.math.sequence.ExpressionTokenType;

public class TokenPattern implements IParserPattern<IToken>
{
	private final ExpressionTokenType type;
	private final Predicate<String> matcher;
	private final Function<String, String> builder;
	
	public TokenPattern(ExpressionTokenType type, Predicate<String> matcher)
	{
		this(type, matcher, (s) -> s);
	}
	
	public TokenPattern(ExpressionTokenType type, Predicate<String> matcher, Function<String, String> builder)
	{
		this.type = type;
		this.matcher = matcher;
		this.builder = builder;
	}
	
	public ExpressionTokenType getType()
	{
		return type;
	}
	
	public Predicate<String> getMatcher()
	{
		return matcher;
	}
	
	public Function<String, String> getBuilder()
	{
		return builder;
	}
	
	@Override
	public boolean isValid(String data)
	{
		return getMatcher().test(data);
	}
	
	@Override
	public IToken compile(String data)
	{
		return new Token(getType(), getBuilder().apply(data));
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "::" + getType();
	}
}
