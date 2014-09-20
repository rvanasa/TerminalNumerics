package net.anasa.util.resolver;

import net.anasa.util.Listing;
import net.anasa.util.resolver.ResolverCache.ICacheEntry;
import net.anasa.util.resolver.logic.IResolver;

public class ParserResolver<I extends ICacheEntry<I>, T> implements IResolver<I, T>
{
	private final ResolverCache<I, T> cache = new ResolverCache<>();
	
	private final Listing<IResolver<I, T>> resolvers = new Listing<>();
	
	public ParserResolver()
	{
		
	}
	
	public ResolverCache<I, T> getCache()
	{
		return cache;
	}

	public Listing<IResolver<I, T>> getResolvers()
	{
		return resolvers;
	}
	
	public void add(IResolver<I, T> resolver)
	{
		getResolvers().add(resolver);
	}
	
	@Override
	public boolean matches(Listing<I> data)
	{
		for(IResolver<I, T> resolver : getResolvers())
		{
			if(resolver.matches(data))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public T resolve(Listing<I> data) throws ResolverException
	{
		T cache = getCache().get(data);
		if(cache != null)
		{
			return cache;
		}
		
		for(IResolver<I, T> resolver : getResolvers())
		{
			if(resolver.matches(data))
			{
				return getCache().cache(data, resolver.resolve(data));
			}
		}
		
		throw new ResolverException("Could not resolve: " + data);
	}
}
