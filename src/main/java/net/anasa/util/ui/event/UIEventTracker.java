package net.anasa.util.ui.event;

import net.anasa.util.Listing;
import net.anasa.util.Mapping;

public class UIEventTracker
{
	private final Mapping<Class<? extends UIEvent>, Listing<IUIListener<?>>> listeners = new Mapping<>();
	
	public UIEventTracker()
	{
		
	}
	
	public <T extends UIEvent> Listing<IUIListener<?>> getListeners(Class<T> clazz)
	{
		if(!listeners.hasKey(clazz))
		{
			listeners.put(clazz, new Listing<>());
		}
		
		return listeners.get(clazz);
	}
	
	public <T extends UIEvent> void register(Class<T> clazz, IUIListener<T> listener)
	{
		if(listener == null)
		{
			return;
		}
		
		getListeners(clazz).add(listener);
	}
	
	public <T extends UIEvent> void remove(Class<T> clazz, IUIListener<T> listener)
	{
		if(listener == null)
		{
			return;
		}
	
		getListeners(clazz).remove(listener);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends UIEvent> void dispatch(T event)
	{
		if(event == null)
		{
			return;
		}
		
		if(listeners != null)
		{
			for(IUIListener<T> listener : getListeners(event.getClass()).conform((listener) -> (IUIListener<T>)listener))
			{
				listener.onEvent(event);
			}
		}
	}
}
