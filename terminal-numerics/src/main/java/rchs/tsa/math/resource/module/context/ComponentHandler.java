package rchs.tsa.math.resource.module.context;

import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.TerminalNumerics;

public class ComponentHandler implements IComponentHandler
{
	private final String ref;
	
	private final IComponent component;
	
	private IComponentHandler parent;
	
	private final Listing<IComponentHandler> inner = new Listing<>();
	
	public ComponentHandler(String ref, IComponent component)
	{
		this.ref = ref;
		this.component = component;
	}
	
	public ModuleContext getContext()
	{
		return TerminalNumerics.getContext();
	}
	
	@Override
	public String getRef()
	{
		return ref;
	}
	
	@Override
	public IComponent getComponent()
	{
		return component;
	}
	
	@Override
	public IComponentHandler getParent()
	{
		return parent;
	}
	
	@Override
	public void setParent(IComponentHandler parent)
	{
		this.parent = parent;
	}
	
	public Listing<IComponentHandler> getInnerHandlers()
	{
		return inner;
	}
	
	public void addInner(IComponentHandler handler)
	{
		getInnerHandlers().add(handler);
	}
	
	@Override
	public IComponentHandler getByRef(String ref)
	{
		return getInnerHandlers().getFirst((inner) -> StringHelper.equalsIgnoreCase(inner.getRef(), ref));
	}
	
	@Override
	public void onAction(String data, boolean propagate) throws Exception
	{
		Debug.log(data + " -- " + propagate + " -- " + this);
		if(data.contains(":"))
		{
			Debug.log(getParent());
			if(propagate && getParent() != null)
			{
				getParent().onAction(data, true);
			}
			else
			{
				int index = data.indexOf(":");
				
				String ref = data.substring(0, index).trim();
				String pass = data.substring(index + 1).trim();
				
				if(getRef() != null && getRef().equalsIgnoreCase(ref))
				{
					getContext().onAction(pass, this);
				}
				
				Debug.log(getInnerHandlers());
				for(IComponentHandler inner : getInnerHandlers().getValues())
				{
					inner.onAction(data, false);
				}
			}
		}
		else
		{
			getContext().onAction(data, this);
		}
	}
	
//	private IComponentHandler getTopParent(IComponentHandler handler)
//	{
//		IComponentHandler parent = handler.getParent();
//		return parent == null ? handler : getTopParent(parent);
//	}
}
