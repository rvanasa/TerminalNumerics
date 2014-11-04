package net.anasa.util.resolver.logic;

import net.anasa.util.Listing;
import net.anasa.util.resolver.IToken;
import net.anasa.util.resolver.ParserResolver;
import net.anasa.util.resolver.ResolverException;

public class ParserMatchResolver<T> implements IResolver<Listing<IToken>>
{
	private final ParserResolver<T> parser;
	
	public ParserMatchResolver(ParserResolver<T> parser)
	{
		this.parser = parser;
	}

	public ParserResolver<T> getParser()
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
