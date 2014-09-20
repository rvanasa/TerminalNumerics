package net.anasa.util.ui;

import javax.swing.JSlider;

import net.anasa.util.ICallback;

public class SliderComponent extends UIActionComponent<JSlider> implements IInputComponent<Integer>
{
	public SliderComponent(int min, int max)
	{
		this(min, max, (min + max) / 2);
	}
	
	public SliderComponent(int min, int max, int value)
	{
		super(new JSlider());
		
		setMin(min);
		setMax(max);
		setValue(value);
		
		getHandle().setFocusable(false);
	}

	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addChangeListener((event) -> callback.call());
	}

	@Override
	public Integer getValue()
	{
		return getHandle().getValue();
	}

	@Override
	public void setValue(Integer value)
	{
		getHandle().setValue(value);
	}

	public int getMin()
	{
		return getHandle().getMinimum();
	}

	public void setMin(int min)
	{
		getHandle().setMinimum(min);
	}

	public int getMax()
	{
		return getHandle().getMaximum();
	}

	public void setMax(int max)
	{
		getHandle().setMaximum(max);
	}
}
