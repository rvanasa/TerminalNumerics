package net.anasa.util;

public final class ProgressWatcher
{
	private static final Mapping<String, ProgressWatcher> ITEMS = new Mapping<>();
	
	public static ProgressWatcher start(String id, int max)
	{
		ProgressWatcher item = new ProgressWatcher(id, max);
		ITEMS.put(item.getID(), item);
		
		return item;
	}
	
	public static boolean complete(String id)
	{
		ProgressWatcher item = get(id);
		ITEMS.remove(id);
		
		if(item != null)
		{
			return item.isComplete();
		}
		
		return false;
	}
	
	public static boolean isComplete(String id)
	{
		return !ITEMS.hasKey(id);
	}
	
	public static ProgressWatcher get(String id)
	{
		return ITEMS.get(id);
	}
	
	public static void set(String id, int progress)
	{
		ProgressWatcher item = ITEMS.get(id);
		if(item != null)
		{
			item.setValue(progress);
		}
	}
	
	public static void increment(String id)
	{
		ProgressWatcher item = get(id);
		if(item != null)
		{
			set(id, item.getValue() + 1);
		}
		
		if(item.isComplete())
		{
			complete(id);
		}
	}
	
	private final String id;
	
	private int value;
	private int max;
	
	private ProgressWatcher(String id, int max)
	{
		this(id, 0, max);
	}
	
	private ProgressWatcher(String id, int value, int max)
	{
		this.id = id;
		
		setValue(value);
		
		this.max = max;
	}
	
	public String getID()
	{
		return id;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public int getMax()
	{
		return max;
	}
	
	public void setMax(int max)
	{
		this.max = max;
	}
	
	public double getPercent()
	{
		return (double)getValue() / getMax();
	}
	
	public boolean isComplete()
	{
		return getValue() >= getMax();
	}
}
