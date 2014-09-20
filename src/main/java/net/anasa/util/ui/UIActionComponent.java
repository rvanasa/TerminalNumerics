package net.anasa.util.ui;

import java.awt.Component;

import net.anasa.util.ICallback;
import net.anasa.util.ui.event.ActionEvent;
import net.anasa.util.ui.event.IUIListener;

public abstract class UIActionComponent<T extends Component> extends UIComponent<T> implements IActionComponent
{
	public UIActionComponent(T handle)
	{
		super(handle);
		
		setupActionCallback(() -> onAction());
	}
	
	protected abstract void setupActionCallback(ICallback callback);
	
	public void addActionListener(IUIListener<ActionEvent> listener)
	{
		getEvents().register(ActionEvent.class, listener);
	}
	
	@Override
	public void addActionListener(ICallback callback)
	{
		if(callback == null)
		{
			return;
		}
		
		addActionListener((event) -> callback.call());
	}
	
	@Override
	public void onAction()
	{
		getEvents().dispatch(new ActionEvent(this));
	}
}
