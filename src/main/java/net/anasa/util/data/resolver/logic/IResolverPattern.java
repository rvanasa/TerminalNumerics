package net.anasa.util.data.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;

public interface IResolverPattern
{
	public boolean matches(Listing<IToken> data);
}
