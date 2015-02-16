package rchs.tsa.math.ui;

import net.anasa.util.Listing;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.SelectionComponent;
import net.anasa.util.ui.TextFieldComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIVerticalLayout;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.system.Graph;
import rchs.tsa.math.util.Evaluator;

public class GraphInputComponent extends PanelComponent
{
	private final GraphComponent graph;
	
	private final TextFieldComponent input;
	
	private final SelectionComponent<Graph> selectedGraph;
	
	private final Listing<CalculationComponent<?>> calculations = new Listing<>();
	
	public GraphInputComponent(MathData mathData)
	{
		graph = new GraphComponent(mathData);
		
		input = new TextFieldComponent((data) -> getGraph().updateGraphs(data));
		input.setToolTipText("<html>Input an expression to be displayed on the graph. (Example: -5x+3^x)<br/>You can graph multiple functions by seperating each expression with a semicolon (;).<br/>Right-click for additional configuration options.");
		input.setContextMenu(graph.getPanel().getContextMenu());
		
		selectedGraph = new SelectionComponent<>((value) -> getGraph().getFunctionColor(getGraph().getGraphs().indexOf(value)).getName());
		selectedGraph.addActionListener(() -> calculate());
		selectedGraph.setWidth(100);
		
		PanelComponent top = new PanelComponent(new UIBorderLayout()
				.set(BorderPosition.LEFT, new LabelComponent(" f(x) = "))
				.set(BorderPosition.CENTER, input)
				.set(BorderPosition.RIGHT, new ButtonComponent("Graph", () -> getGraph().updateGraphs(getInput().getValue()))));
		top.setBorder(2, 2);
		
		PropertyFieldComponent<Graph> graphSelect = new PropertyFieldComponent<>("Selected Graph", selectedGraph);
		graph.addGraphListener((event) -> updateGraphSelection());
		
		PanelComponent calcPanel = new PanelComponent();
		calcPanel.setBorder(4, 2);
		
		calculations.add(new CalculationComponent<>("Solve for x", new TextFieldComponent(16), (data) -> getSelectedGraph() != null ? getSelectedGraph().getFrom(Evaluator.parse(getMathData(), data).evaluate(getMathData()), getMathData()) : null));
		
		UIVerticalLayout calcLayout = new UIVerticalLayout();
		calcLayout.add(graphSelect);
		for(CalculationComponent<?> component : calculations)
		{
			calcLayout.add(component);
		}
		calcLayout.apply(calcPanel);
		
		new UIBorderLayout()
				.set(BorderPosition.TOP, top)
				.set(BorderPosition.CENTER, graph)
				.set(BorderPosition.BOTTOM, calcPanel)
				.apply(this);
	}
	
	public GraphComponent getGraph()
	{
		return graph;
	}
	
	public TextFieldComponent getInput()
	{
		return input;
	}
	
	public MathData getMathData()
	{
		return getGraph().getMathData();
	}
	
	public Graph getSelectedGraph()
	{
		return selectedGraph.getValue();
	}
	
	public int getSelectedGraphIndex()
	{
		return selectedGraph.getIndex();
	}
	
	private void updateGraphSelection()
	{
		selectedGraph.setValues(getGraph().getGraphs());
		calculate();
	}
	
	private void calculate()
	{
		for(CalculationComponent<?> component : calculations)
		{
			component.calculate();
		}
	}
}