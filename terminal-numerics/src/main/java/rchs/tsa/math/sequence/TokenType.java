package rchs.tsa.math.sequence;

import net.anasa.util.StringHelper;
import net.anasa.util.data.resolver.IToken;

public enum TokenType
{
	NUMBER,
	OPERATOR,
	FUNCTION,
	VARIABLE,
	OPEN_PARENTHESIS,
	CLOSE_PARENTHESIS,
	EQUALS,
	GREATER_THAN,
	LESS_THAN,
	GREATER_THAN_EQUAL,
	LESS_THAN_EQUAL;
	
	public boolean isType(IToken token)
	{
		return isType(token.getType());
	}
	
	public boolean isType(String type)
	{
		return name().equalsIgnoreCase(type);
	}
	
	public static TokenType getFrom(String type)
	{
		return valueOf(StringHelper.upperCase(type));
	}
	
	public static TokenType getFrom(IToken token)
	{
		return getFrom(token.getType());
	}
}