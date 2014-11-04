package net.anasa.util.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.resolver.IToken;
import net.anasa.util.resolver.ResolverException;

public interface IResolver<T> extends IPattern
{
	public T resolve(Listing<IToken> data) throws ResolverException;
}