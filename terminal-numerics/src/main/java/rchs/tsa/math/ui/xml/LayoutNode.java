package rchs.tsa.math.ui.xml;

import net.anasa.util.Mapping;
import net.anasa.util.data.FormatException;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.ILayout;
import rchs.tsa.math.resource.module.context.ComponentHandler;
import rchs.tsa.math.resource.module.context.IComponentHandler;
import rchs.tsa.math.resource.module.context.ModuleContext;

public class LayoutNode implements ILayoutNode
{
	private final String ref;
	
	private final Mapping<ILayoutNode, String> positions = new Mapping<>();
	
	private final ILayout layout;
	
	public LayoutNode(String ref, ILayout layout)
	{
		this.ref = ref;
		
		this.layout = layout;
	}
	
	@Override
	public String getRef()
	{
		return ref;
	}
	
	public ILayout getLayout()
	{
		return layout;
	}
	
	public Mapping<ILayoutNode, String> getPositions()
	{
		return positions;
	}
	
	public void add(ILayoutNode node, String pos)
	{
		getPositions().put(node, pos);
	}
	
	@Override
	public IComponentHandler compile(ModuleContext context) throws FormatException
	{
		PanelComponent panel = new PanelComponent();
		ComponentHandler handler = new ComponentHandler(getRef(), panel);
		
		for(ILayoutNode node : getPositions().getKeys())
		{
			IComponentHandler inner = node.compile(context);
			
			getLayout().set(getPositions().get(node), inner.getComponent());
			
			handler.addInner(inner);
			inner.setParent(handler);
		}
		
		getLayout().apply(panel);
		
		return handler;
	}
}
