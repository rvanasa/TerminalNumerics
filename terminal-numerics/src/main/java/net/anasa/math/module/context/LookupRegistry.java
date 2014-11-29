package net.anasa.math.module.context;

import net.anasa.util.Checks;
import net.anasa.util.Lookup;

abstract class LookupRegistry<T> implements IRegistry<T>
{
	private final Lookup<T> entries = new Lookup<>();
	
	public LookupRegistry()
	{
		
	}
	
	public Lookup<T> getEntries()
	{
		return entries;
	}
	
	@Override
	public T getByID(String id)
	{
		return getEntries().get(id);
	}

	public void register(String id, T entry)
	{
		Checks.checkNotNull(id, "Entry ID cannot be null");
		Checks.checkNotNull(entry, "Entry cannot be null");
		
		getEntries().put(id.toLowerCase(), entry);
	}
}
