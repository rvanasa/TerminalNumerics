package net.anasa.math.ui;

import net.anasa.math.MathException;
import net.anasa.math.expression.IExpression;
import net.anasa.math.expression.OperationExpression;
import net.anasa.math.expression.OperatorType;
import net.anasa.math.graph.Graph;
import net.anasa.math.util.Evaluator;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.TextFieldComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class TransformationComponent extends PanelComponent
{
	private final GraphComponent graph;
	
	private final PropertyFieldComponent<String> graphData;
	private final PropertyFieldComponent<String> verticalTranslation;
	private final PropertyFieldComponent<String> verticalScale;
	
	public TransformationComponent()
	{
		graph = new GraphComponent();
		
		PanelComponent panel = new PanelComponent();
		
		UIVerticalLayout panelLayout = new UIVerticalLayout();
		panelLayout.add(graphData = new PropertyFieldComponent<>("Original graph", new TextFieldComponent((data) -> resolveGraph())));
		panelLayout.add(verticalTranslation = new PropertyFieldComponent<>("Vertical translation", new TextFieldComponent((data) -> resolveGraph())));
		panelLayout.add(verticalScale = new PropertyFieldComponent<>("Vertical scale", new TextFieldComponent((data) -> resolveGraph())));
		panelLayout.apply(panel);
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, graph);
		layout.set(BorderPosition.BOTTOM, panel);
		layout.apply(this);
	}
	
	public GraphComponent getGraph()
	{
		return graph;
	}
	
	private void resolveGraph()
	{
		try
		{
			IExpression expression = Evaluator.evaluate(graphData.getValue());
			
			expression = new OperationExpression(OperatorType.MULTIPLY, expression, Evaluator.evaluate(verticalScale.getValue()));
			expression = new OperationExpression(OperatorType.ADD, expression, Evaluator.evaluate(verticalTranslation.getValue()));
			
			getGraph().setGraph(new Graph(expression));
		}
		catch(MathException e)
		{
			e.printStackTrace();
		}
	}
}
