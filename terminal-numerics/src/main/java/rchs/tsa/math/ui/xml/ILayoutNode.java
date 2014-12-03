package rchs.tsa.math.ui.xml;

import rchs.tsa.math.module.context.ModuleContext;
import net.anasa.util.data.FormatException;
import net.anasa.util.ui.IComponent;

public interface ILayoutNode
{
	public IComponent compile(ModuleContext context) throws FormatException;
}
