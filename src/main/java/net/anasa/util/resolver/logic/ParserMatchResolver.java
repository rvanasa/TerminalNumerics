package net.anasa.util.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.resolver.ParserResolver;
import net.anasa.util.resolver.ResolverCache.ICacheEntry;
import net.anasa.util.resolver.ResolverException;

public class ParserMatchResolver<I extends ICacheEntry<I>, T> implements IResolver<I, Listing<I>>
{
	private final ParserResolver<I, T> parser;
	
	public ParserMatchResolver(ParserResolver<I, T> parser)
	{
		this.parser = parser;
	}

	public ParserResolver<I, T> getParser()
	{
		return parser;
	}

	@Override
	public boolean matches(Listing<I> data)
	{
		return getParser().matches(data);
	}

	@Override
	public Listing<I> resolve(Listing<I> data) throws ResolverException
	{
		return data;
	}
}
