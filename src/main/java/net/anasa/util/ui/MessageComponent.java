package net.anasa.util.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class MessageComponent extends UIComponent<JOptionPane>
{
	private final String title;
	
	public MessageComponent(String message)
	{
		this("Message", message);
	}
	
	public MessageComponent(String title, String message)
	{
		super(new JOptionPane(message));
		
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getMessage()
	{
		return String.valueOf(getHandle().getMessage());
	}
	
	public void display()
	{
		JDialog dialog = getHandle().createDialog(getTitle());
		dialog.pack();
		dialog.setVisible(true);
	}
}
