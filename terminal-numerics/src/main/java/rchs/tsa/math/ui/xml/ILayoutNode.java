package rchs.tsa.math.ui.xml;

import net.anasa.util.data.FormatException;
import rchs.tsa.math.resource.module.context.IComponentHandler;
import rchs.tsa.math.resource.module.context.ModuleContext;

public interface ILayoutNode
{
	public String getRef();
	
	public IComponentHandler compile(ModuleContext context) throws FormatException;
}
