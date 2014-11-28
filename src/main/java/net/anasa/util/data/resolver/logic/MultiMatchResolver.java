package net.anasa.util.data.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.MultiResolver;
import net.anasa.util.data.resolver.ResolverException;

public class MultiMatchResolver<T> implements IResolver<Listing<IToken>>
{
	private final MultiResolver<T> parser;
	
	public MultiMatchResolver(MultiResolver<T> parser)
	{
		this.parser = parser;
	}

	public MultiResolver<T> getParser()
	{
		return parser;
	}

	@Override
	public boolean matches(Listing<IToken> data)
	{
		return getParser().matches(data);
	}

	@Override
	public Listing<IToken> resolve(Listing<IToken> data) throws ResolverException
	{
		return data;
	}
}
