package net.anasa.util;

public class Progress
{
	private int maxValue;
	private int value;
	
	public Progress()
	{
		this(0, 0);
	}
	
	public Progress(int maxValue)
	{
		this(maxValue, 0);
	}
	
	public Progress(int maxValue, int value)
	{
		setMaxValue(maxValue);
		setValue(value);
	}
	
	public int getMaxValue()
	{
		return maxValue;
	}
	
	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public void increment()
	{
		setValue(getValue() + 1);
	}
	
	public boolean isEnabled()
	{
		return getValue() >= 0 && getMaxValue() > 0;
	}
	
	public boolean isComplete()
	{
		return getValue() >= getMaxValue();
	}
	
	public void setComplete()
	{
		setValue(getMaxValue());
	}
}
