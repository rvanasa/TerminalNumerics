package net.anasa.util.ui.event;

public interface IUIListener<T extends UIEvent>
{
	public void onEvent(T event);
}
