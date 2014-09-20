package net.anasa.util.ui;

import net.anasa.util.ICallback;

public interface IActionComponent extends IComponent
{
	public void addActionListener(ICallback callback);
	
	public void onAction();
}
