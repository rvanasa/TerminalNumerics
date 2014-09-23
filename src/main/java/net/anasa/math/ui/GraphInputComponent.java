package net.anasa.math.ui;

import net.anasa.math.MathData;
import net.anasa.math.MathException;
import net.anasa.math.graph.Graph;
import net.anasa.math.util.Evaluator;
import net.anasa.util.Listing;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.SelectionComponent;
import net.anasa.util.ui.TextFieldComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class GraphInputComponent extends PanelComponent
{
	private final GraphComponent graph;
	
	private final TextFieldComponent input;
	
	private final SelectionComponent<Graph> selectedGraph;
	
	private final MathData mathData;
	
	private final Listing<CalculationComponent<?>> calculations = new Listing<>();
	
	public GraphInputComponent()
	{
		this(new MathData());
	}
	
	public GraphInputComponent(MathData mathData)
	{
		graph = new GraphComponent();
		
		input = new TextFieldComponent((data) -> getGraph().addGraphs(data));
		input.setToolTipText("<html>Input an expression to be displayed on the graph. (Example: -5x+3^x)<br/>You can graph multiple functions by seperating each expression with a semicolon (;).<html>");
		
		selectedGraph = new SelectionComponent<>((value) -> getGraph().getFunctionColor(getGraph().getGraphs().indexOf(value)).getName());
		selectedGraph.addActionListener(() -> calculate());
		selectedGraph.setWidth(100);
		
		this.mathData = mathData;
		
		PanelComponent top = new PanelComponent();
		top.setBorder(2, 2);
		
		UIBorderLayout topLayout = new UIBorderLayout();
		topLayout.set(BorderPosition.LEFT, new LabelComponent(" f(x) = "));
		topLayout.set(BorderPosition.CENTER, input);
		topLayout.set(BorderPosition.RIGHT, new ButtonComponent("Graph", () -> getGraph().addGraphs(getInput().getValue())));
		topLayout.apply(top);
		
		PropertyFieldComponent<Graph> graphSelect = new PropertyFieldComponent<>("Selected Graph", selectedGraph);
		graph.addGraphListener((event) -> updateGraphSelection());
		
		PanelComponent calcPanel = new PanelComponent();
		calcPanel.setBorder(4, 2);
		
		calculations.add(new CalculationComponent<>("Solve for x", new TextFieldComponent(16), (data) -> {
			try
			{
				return getSelectedGraph() != null ? getSelectedGraph().getFrom(Evaluator.evaluate(data).evaluate(getMathData())) : null;
			}
			catch(MathException e)
			{
				return null;
			}
		}));
		
		UIVerticalLayout calcLayout = new UIVerticalLayout();
		calcLayout.add(graphSelect);
		for(CalculationComponent<?> component : calculations)
		{
			calcLayout.add(component);
		}
		calcLayout.apply(calcPanel);
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, top);
		layout.set(BorderPosition.CENTER, graph);
		layout.set(BorderPosition.BOTTOM, calcPanel);
		layout.apply(this);
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
		return mathData;
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