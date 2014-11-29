package net.anasa.math.ui;

import net.anasa.util.ui.IInputComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class PropertyFieldComponent<T> extends PanelComponent
{
	private final IInputComponent<T> input;
	
	public PropertyFieldComponent(String name, IInputComponent<T> input)
	{
		this.input = input;
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.CENTER, new LabelComponent(name + ": "));
		layout.set(BorderPosition.RIGHT, input);
		layout.apply(this);
	}

	public IInputComponent<T> getInput()
	{
		return input;
	}
	
	public T getValue()
	{
		return getInput().getValue();
	}
	
	public void setValue(T value)
	{
		getInput().setValue(value);
	}
}
