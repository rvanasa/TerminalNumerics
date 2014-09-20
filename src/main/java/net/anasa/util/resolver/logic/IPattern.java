package net.anasa.util.resolver.logic;

import net.anasa.util.Listing;

public interface IPattern<I>
{
	public boolean matches(Listing<I> data);
}
