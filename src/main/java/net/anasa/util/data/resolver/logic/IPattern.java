package net.anasa.util.data.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;

public interface IPattern
{
	public boolean matches(Listing<IToken> data);
}
