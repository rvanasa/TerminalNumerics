package rchs.tsa.math.resource.module.context;

import net.anasa.util.ui.IComponent;

public interface IComponentHandler
{
	public String getRef();
	
	public IComponent getComponent();
	
	public IComponentHandler getParent();
	
	public void setParent(IComponentHandler parent);
	
	public IComponentHandler getByRef(String ref);
	
	public void onAction(String data, boolean propagate) throws Exception;
}