package net.anasa.util.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.resolver.IToken;

public interface IPattern
{
	public boolean matches(Listing<IToken> data);
}
