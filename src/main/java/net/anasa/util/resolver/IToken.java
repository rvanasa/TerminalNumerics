package net.anasa.util.resolver;

import net.anasa.util.StringHelper;

public interface IToken
{
	public String getType();
	
	public String getData();
	
	public default boolean isEquivalent(IToken other)
	{
		return StringHelper.equals(getType(), other.getType()) && StringHelper.equals(getData(), other.getData());
	}
}
