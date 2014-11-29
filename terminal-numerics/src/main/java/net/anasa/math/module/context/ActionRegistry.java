package net.anasa.math.module.context;

import net.anasa.math.module.context.ActionRegistry.IComponentAction;
import net.anasa.util.Debug;
import net.anasa.util.ui.IActionComponent;

public class ActionRegistry extends LookupRegistry<IComponentAction>
{
	public void onAction(String id, IActionComponent component)
	{
		IComponentAction action = getByID(id);
		if(action != null)
		{
			try
			{
				action.onAction(component);
			}
			catch(Exception e)
			{
				Debug.err("Failed to run action: " + id);
				e.printStackTrace();
			}
		}
	}
	
	public interface IComponentAction
	{
		public void onAction(IActionComponent component) throws Exception;
	}
}
