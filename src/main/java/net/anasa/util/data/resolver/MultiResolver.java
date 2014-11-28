package net.anasa.util.data.resolver;

import net.anasa.util.Listing;
import net.anasa.util.data.resolver.logic.IResolver;

public class MultiResolver<T> implements IResolver<T>
{
	private final ResolverCache<T> cache = new ResolverCache<>();
	
	private final Listing<IResolver<T>> resolvers = new Listing<>();
	
	public MultiResolver()
	{
		
	}
	
	public ResolverCache<T> getCache()
	{
		return cache;
	}

	public Listing<IResolver<T>> getResolvers()
	{
		return resolvers;
	}
	
	public void add(IResolver<T> resolver)
	{
		getResolvers().add(resolver);
	}
	
	@Override
	public boolean matches(Listing<IToken> data)
	{
		for(IResolver<T> resolver : getResolvers())
		{
			if(resolver.matches(data))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public T resolve(Listing<IToken> data) throws ResolverException
	{
		T cache = getCache().get(data);
		if(cache != null)
		{
			return cache;
		}
		
		for(IResolver<T> resolver : getResolvers())
		{
			if(resolver.matches(data))
			{
				return getCache().cache(data, resolver.resolve(data));
			}
		}
		
		throw new ResolverException("Could not resolve: " + data);
	}
}
