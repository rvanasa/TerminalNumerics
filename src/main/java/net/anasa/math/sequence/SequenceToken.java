package net.anasa.math.sequence;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.resolver.ResolverCache.ICacheEntry;
import net.anasa.util.resolver.ResolverException;
import net.anasa.util.resolver.logic.IResolver;

public class SequenceToken implements ICacheEntry<SequenceToken>
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
	
	public TokenType getType()
	{
		return type;
	}
	
	public boolean hasData()
	{
		return getData() != null;
	}
	
	public String getData()
	{
		return data;
	}

	@Override
	public boolean isEquivalent(SequenceToken other)
	{
		return getType() == other.getType() && StringHelper.equals(getData(), other.getData());
	}
	
	@Override
	public String toString()
	{
		return "(" + getType() + (hasData() ? " : " + getData() : "") + ")";
	}
	
	public enum TokenType implements IResolver<SequenceToken, SequenceToken>
	{
		NUMBER,
		OPERATOR,
		FUNCTION,
		VARIABLE,
		OPEN_PARENTHESIS,
		CLOSE_PARENTHESIS;

		@Override
		public boolean matches(Listing<SequenceToken> data)
		{
			return data.size() == 1 && data.get(0).getType() == this;
		}

		@Override
		public SequenceToken resolve(Listing<SequenceToken> data) throws ResolverException
		{
			return data.get(0);
		}
	}
}
