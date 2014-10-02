package net.anasa.math.module.context;

import net.anasa.util.Checks;
import net.anasa.util.Mapping;

abstract class AbstractRegistry<T> implements IRegistry<T>
{
	private final Mapping<String, T> entries = new Mapping<>();
	
	public AbstractRegistry()
	{
		
	}
	
	public Mapping<String, T> getEntries()
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
		Checks.checkNotNull(id, "Entry id cannot be null");
		Checks.checkNotNull(entry, "Entry cannot be null");
		
		getEntries().put(id.toLowerCase(), entry);
	}
}
