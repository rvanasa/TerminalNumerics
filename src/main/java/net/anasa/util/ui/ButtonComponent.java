package net.anasa.util.ui;

import javax.swing.JButton;

import net.anasa.util.ICallback;

public class ButtonComponent extends UIActionComponent<JButton>
{
	public ButtonComponent(String text)
	{
		this(text, null);
	}
	
	public ButtonComponent(String text, ICallback callback)
	{
		super(new JButton());
		
		setText(text);
		
		if(callback != null)
		{
			addActionListener((event) -> callback.call());
		}
		
		getHandle().setFocusable(false);
	}

	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addActionListener((event) -> callback.call());
	}
	
	public String getText()
	{
		return getHandle().getText();
	}
	
	public void setText(String text)
	{
		getHandle().setText(text);
	}
}
