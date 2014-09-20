package net.anasa.util.ui;

public interface IInputComponent<T> extends IActionComponent
{
	public T getValue();
	
	public void setValue(T value);
}
