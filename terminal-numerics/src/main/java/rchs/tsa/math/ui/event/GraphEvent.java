package rchs.tsa.math.ui.event;

import rchs.tsa.math.system.Graph;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.event.UIEvent;

public class GraphEvent extends UIEvent
{
	private final Graph graph;
	
	public GraphEvent(IComponent component, Graph graph)
	{
		super(component);
		
		this.graph = graph;
	}

	public Graph getGraph()
	{
		return graph;
	}
}
