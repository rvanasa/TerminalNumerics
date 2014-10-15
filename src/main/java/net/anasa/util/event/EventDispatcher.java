package net.anasa.util.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.event.EventHandler.EventListener;

public class EventDispatcher
{
	private final Listing<EventBuffer> listeners = new Listing<EventBuffer>().setHandle(new CopyOnWriteArrayList<>());
	
	public Listing<EventBuffer> getListeners()
	{
		return listeners;
	}
	
	public void register(IEventListener listener)
	{
		if(!getListeners().contains((buffer) -> buffer.getListener() == listener) && listener != null)
		{
			try
			{
				getListeners().add(new EventBuffer(listener));
			}
			catch(EventException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Debug.log("Attempted to register invalid event listener: " + listener);
		}
	}
	
	public void dispatch(IEvent event)
	{
		boolean flag = false;
		
		for(EventBuffer buffer : getListeners())
		{
			IEventListener listener = buffer.getListener();
			
			if(listener.isListening())
			{
				for(Method method : buffer.getListeners(event.getClass()))
				{
					try
					{
						method.invoke(listener, event);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						listener.onFailEvent(event, e);
					}
				}
			}
			else
			{
				flag = true;
			}
		}
		
		if(flag)
		{
			clean();
		}
	}
	
	public void clean()
	{
		getListeners().filter((buffer) -> buffer.getListener().isListening());
	}
	
	public interface IEventListener
	{
		public boolean isListening();
		
		public default void onFailEvent(IEvent event, Throwable e)
		{
			
		}
	}
	
	private static class EventBuffer
	{
		private final IEventListener listener;
		
		private final HashMap<Class<?>, Collection<Method>> map = new HashMap<>();
		
		public EventBuffer(IEventListener listener) throws EventException
		{
			Checks.checkNotNull(listener, new EventException("Event listener cannot be null"));
			
			this.listener = listener;
			
			for(Method method : listener.getClass().getMethods())
			{
				if(method.isAnnotationPresent(EventListener.class))
				{
					registerMethod(method);
				}
			}
			
			for(Method method : listener.getClass().getDeclaredMethods())
			{
				if(method.isAnnotationPresent(EventListener.class))
				{
					registerMethod(method);
				}
			}
		}
		
		private void registerMethod(Method method) throws EventException
		{
			method.setAccessible(true);
			
			Checks.check(method.getParameterCount() == 1, new EventException("Event listener methods can only contain one argument"));
			Checks.check(IEvent.class.isAssignableFrom(method.getParameterTypes()[0]), new EventException("Event listener methods can only be passed Event objects"));
			
			Collection<Method> listeners = getListeners(method.getParameterTypes()[0]);
			
			if(!listeners.contains(method))
			{
				listeners.add(method);
			}
		}
		
		public IEventListener getListener()
		{
			return listener;
		}
		
		public HashMap<Class<?>, Collection<Method>> getMap()
		{
			return map;
		}
		
		public Collection<Method> getListeners(Class<?> clazz)
		{
			if(!getMap().containsKey(clazz))
			{
				getMap().put(clazz, new ArrayList<>());
			}
			
			return getMap().get(clazz);
		}
	}
	
	public static class EventException extends Exception
	{
		public EventException(String message)
		{
			super(message);
		}
		
		public EventException(Throwable e)
		{
			super(e);
		}
	}
}
