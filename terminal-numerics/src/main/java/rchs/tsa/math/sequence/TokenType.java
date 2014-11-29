package rchs.tsa.math.sequence;

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
	
	public boolean isType(String type)
	{
		return name().equals(type);
	}
	
	public static TokenType getFrom(String type)
	{
		return valueOf(type);
	}
}