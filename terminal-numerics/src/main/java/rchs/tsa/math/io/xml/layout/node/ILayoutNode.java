package rchs.tsa.math.io.xml.layout.node;

import net.anasa.util.data.FormatException;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.module.context.ModuleContext;

public interface ILayoutNode
{
	public String getRef();
	
	public IComponent compile(ModuleContext context) throws FormatException;
}
