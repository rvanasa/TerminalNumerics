package rchs.tsa.math.resource.module.internal.graph;

import net.anasa.util.Listing;
import net.anasa.util.NumberHelper;
import net.anasa.util.StringHelper;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.module.AbstractModule;
import rchs.tsa.math.resource.module.IModuleDelegate;
import rchs.tsa.math.ui.GraphComponent;
import rchs.tsa.math.ui.GraphInputComponent;
import rchs.tsa.math.ui.GraphModelComponent;
import rchs.tsa.math.ui.GraphModelComponent.ModelVariable;
import rchs.tsa.math.util.Evaluator;

public class GraphModule extends AbstractModule implements IModuleDelegate
{
	public GraphModule()
	{
		super("graph_module", new Version(1, 0, 0), "Graph Module", "Graphical display and evaluation functionality");
	}
	
	@Override
	public void init()
	{
		addComponent("graph", (props) -> new GraphComponent(props.getString("display", "")));
		addComponent("graph_input", (props) -> new GraphInputComponent());
		addComponent("graph_model", (props) -> {
			IMathExpression expression = Evaluator.parse(props.getString("model"));
			ModelVariable[] vars = new Listing<>(props.getString("input").split("[,; ]"))
					.filter((var) -> !var.trim().isEmpty())
					.conform((var) -> {
						int index = var.indexOf(":");
						if(index == -1)
						{
							return new ModelVariable(var);
						}
						else
						{
							return new ModelVariable(var.substring(0, var.indexOf(":")), NumberHelper.getDouble(var.substring(var.indexOf(":") + 1)));
						}
					})
					.toArray(ModelVariable.class);
			return new GraphModelComponent(props.getString("display", props.getString("model")), expression, vars);
		});
		
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
