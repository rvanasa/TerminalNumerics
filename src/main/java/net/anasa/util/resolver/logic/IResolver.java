package net.anasa.util.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.resolver.ResolverException;

public interface IResolver<I, T> extends IPattern<I>
{
	public T resolve(Listing<I> data) throws ResolverException;
}