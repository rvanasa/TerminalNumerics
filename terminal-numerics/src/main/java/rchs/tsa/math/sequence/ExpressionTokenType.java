package rchs.tsa.math.sequence;

import net.anasa.util.data.resolver.ITokenType;

public enum ExpressionTokenType implements ITokenType
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
	
	@Override
	public String getName()
	{
		return name();
	}
}