package net.anasa.util;

public class Lookup<T> extends Mapping<String, T>
{
	public Lookup()
	{
		
	}
	
	@Override
	public Lookup<T> put(String key, T value)
	{
		super.put(key, value);
		
		return this;
	}
}
