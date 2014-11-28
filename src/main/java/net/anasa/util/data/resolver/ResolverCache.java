package net.anasa.util.data.resolver;

import java.util.HashMap;
import java.util.Map;

import net.anasa.util.Listing;

public class ResolverCache<T>
{
	private final Map<Listing<IToken>, T> map = new HashMap<>();
	
	public Map<Listing<IToken>, T> getMap()
	{
		return map;
	}
	
	public boolean isCached(Listing<IToken> data)
	{
		return get(data) != null;
	}
	
	public T get(Listing<IToken> data)
	{
		loop: for(Listing<IToken> list : getMap().keySet())
		{
			if(list.size() == data.size())
			{
				for(int i = 0; i < list.size(); i++)
				{
					if(!data.get(i).isEquivalent(list.get(i)))
					{
						continue loop;
					}
				}
				
				return getMap().get(list);
			}
		}
		
		return null;
	}
	
	public T cache(Listing<IToken> data, T value)
	{
		getMap().putIfAbsent(data, value);
		return value;
	}
}
