package net.anasa.util;

public final class Progress
{
	private static final Mapping<String, Progress> ITEMS = new Mapping<>();
	
	public static Progress start(String id, int max)
	{
		Progress item = new Progress(id, max);
		ITEMS.put(item.getID(), item);
		
		return item;
	}
	
	public static boolean complete(String id)
	{
		Progress item = get(id);
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
	
	public static Progress get(String id)
	{
		return ITEMS.get(id);
	}
	
	public static void set(String id, int progress)
	{
		Progress item = ITEMS.get(id);
		if(item != null)
		{
			item.setValue(progress);
		}
	}
	
	public static void increment(String id)
	{
		Progress item = get(id);
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
	
	private Progress(String id, int max)
	{
		this(id, 0, max);
	}
	
	private Progress(String id, int value, int max)
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
