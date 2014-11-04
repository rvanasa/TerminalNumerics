package net.anasa.math.sequence;

import net.anasa.util.Listing;
import net.anasa.util.resolver.IToken;
import net.anasa.util.resolver.ResolverException;
import net.anasa.util.resolver.logic.IResolver;

public class SequenceToken implements IToken
{
	private final TokenType type;
	private final String data;
	
	public SequenceToken(TokenType type)
	{
		this(type, null);
	}
	
	public SequenceToken(TokenType type, String data)
	{
		this.type = type;
		this.data = data;
	}
	
	public TokenType getTypeValue()
	{
		return type;
	}
	
	@Override
	public String getType()
	{
		return getTypeValue().name();
	}
	
	public boolean hasData()
	{
		return getData() != null;
	}
	
	@Override
	public String getData()
	{
		return data;
	}
	
	@Override
	public String toString()
	{
		return "(" + getType() + (hasData() ? " : " + getData() : "") + ")";
	}
	
	public enum TokenType implements IResolver<IToken>
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
		public boolean matches(Listing<IToken> data)
		{
			return data.size() == 1 && isType(data.get(0).getType());
		}
		
		public boolean isType(String type)
		{
			return name().equals(type);
		}

		@Override
		public IToken resolve(Listing<IToken> data) throws ResolverException
		{
			return data.get(0);
		}
		
		public static TokenType getFrom(String type)
		{
			return valueOf(type);
		}
	}
}
