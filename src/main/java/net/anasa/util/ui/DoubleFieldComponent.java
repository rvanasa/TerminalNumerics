package net.anasa.util.ui;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class DoubleFieldComponent extends NumberFieldComponent<Double>
{
	public DoubleFieldComponent()
	{
		this(Double.valueOf(0));
	}
	
	public DoubleFieldComponent(double value)
	{
		this(value, 1);
	}
	
	public DoubleFieldComponent(double value, double step)
	{
		this(value, null, null, step);
	}
	
	public DoubleFieldComponent(Double value, Double min, Double max, Double step)
	{
		this(new SpinnerNumberModel(value, min, max, step));
	}
	
	public DoubleFieldComponent(SpinnerModel model)
	{
		super(model);
	}
}
