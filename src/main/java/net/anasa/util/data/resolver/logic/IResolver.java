package net.anasa.util.data.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;

public interface IResolver<T> extends IResolverPattern
{
	public T resolve(Listing<IToken> data) throws ResolverException;
}