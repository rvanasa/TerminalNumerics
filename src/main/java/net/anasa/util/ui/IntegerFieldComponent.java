package net.anasa.util.ui;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class IntegerFieldComponent extends NumberFieldComponent<Integer>
{
	public IntegerFieldComponent()
	{
		this(Integer.valueOf(0));
	}
	
	public IntegerFieldComponent(int value)
	{
		this(value, 1);
	}
	
	public IntegerFieldComponent(int value, int step)
	{
		this(value, null, null, step);
	}
	
	public IntegerFieldComponent(Integer value, Integer min, Integer max, Integer step)
	{
		this(new SpinnerNumberModel(value, min, max, step));
	}
	
	public IntegerFieldComponent(SpinnerModel model)
	{
		super(model);
	}
}
