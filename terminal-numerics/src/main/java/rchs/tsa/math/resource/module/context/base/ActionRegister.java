package rchs.tsa.math.resource.module.context.base;

import net.anasa.util.Debug;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.module.context.base.ActionRegister.IComponentAction;

public class ActionRegister extends LookupRegister<IComponentAction>
{
	public void onAction(String id, IComponent component, String[] args)
	{
		IComponentAction action = getByID(id);
		if(action != null)
		{
			try
			{
				action.onAction(component, args);
			}
			catch(Exception e)
			{
				Debug.err("Failed to run action: " + id);
				e.printStackTrace();
			}
		}
	}

	public String getIDFromData(String data)
	{
		data = data.trim();
		return data.contains(" ") ? data.substring(0, data.indexOf(" ")) : data;
	}

	public String[] getArgsFromData(String data)
	{
		data = data.trim();
		return data.contains(" ") ? data.substring(data.indexOf(" ") + 1).split(" ") : new String[0];
	}
	
	public interface IComponentAction
	{
		public void onAction(IComponent component, String[] args) throws Exception;
	}
}
