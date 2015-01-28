package rchs.tsa.math.resource.module.internal.graph;

import net.anasa.util.StringHelper;
import net.anasa.util.data.properties.Properties;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.module.AbstractModule;
import rchs.tsa.math.resource.module.IModuleDelegate;
import rchs.tsa.math.ui.GraphComponent;
import rchs.tsa.math.ui.GraphInputComponent;

public class GraphModule extends AbstractModule implements IModuleDelegate
{
	public GraphModule()
	{
		super("graph_module", new Version(1, 0, 0), "Graph Module", "Graphical display and evaluation functionality");
	}
	
	@Override
	public void init()
	{
		addComponent("graph", (Properties props) -> new GraphComponent(props.getString("display", "")));
		addComponent("graph_input", (Properties props) -> new GraphInputComponent());
		
		addAction("update_graph", (component, args) -> {
			String data = StringHelper.join(" ", args);
			if(component instanceof GraphComponent)
			{
				((GraphComponent)component).updateGraphs(data);
			}
		});
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return this;
	}
}
