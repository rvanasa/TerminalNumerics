package net.anasa.math.ui.xml;

import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.ui.IComponent;

public interface ILayoutNode
{
	public IComponent compile(ModuleContext context) throws FormatException;
}
