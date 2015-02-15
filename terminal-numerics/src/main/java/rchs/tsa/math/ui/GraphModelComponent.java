package rchs.tsa.math.ui;

import java.awt.Font;

import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.SliderComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIFlowLayout;
import net.anasa.util.ui.layout.UIGridLayout;
import net.anasa.util.ui.layout.UIVerticalLayout;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.expression.MathNumber;
import rchs.tsa.math.graph.Graph;

public class GraphModelComponent extends PanelComponent
{
	private final IMathExpression expression;
	
	private final GraphComponent graph;
	
	private final LabelComponent equationLabel;
	
	private final PanelComponent inputPanel;
	
	public GraphModelComponent(String display, IMathExpression expression, ModelVariable... vars)
	{
		this.expression = expression;
		
		this.graph = new GraphComponent();
		
		this.equationLabel = new LabelComponent();
		
		this.inputPanel = new PanelComponent();
		
		UIVerticalLayout layout = new UIVerticalLayout();
		for(ModelVariable var : vars)
		{
			int scale = 10;
			LabelComponent value = new LabelComponent();
			value.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
			value.setBorder(10, 0);
			SliderComponent field = new SliderComponent(-10 * scale, 10 * scale, (int)(var.getDefault() * scale));
			field.addActionListener(() -> {
				double val = field.getValue() / (double)scale;
				value.setText(String.valueOf(val));
				getMathData().setVariable(var.getVariable(), new MathNumber(val));
				updateGraph();
			});
			field.onAction();
			PanelComponent panel = new PanelComponent(new UIGridLayout()
					.setPaddingX(10)
					.add(0, 0, new LabelComponent(var.getVariable(), new Font(Font.SERIF, Font.BOLD, 14)))
					.add(1, 0, value)
					.add(2, 0, field));
			panel.setBorder(10, 4);
			layout.add(panel);
		}
		layout.apply(inputPanel);
		
		new UIBorderLayout()
				.set(BorderPosition.TOP, new PanelComponent(new UIFlowLayout().add(new LabelComponent("<html><body><i>f(x)</i> = " + display, new Font(Font.SANS_SERIF, Font.PLAIN, 16)))))
				.set(BorderPosition.CENTER, graph)
				.set(BorderPosition.BOTTOM, inputPanel)
				.apply(this);
		
		this.graph.setGraph(new Graph(getExpression()));
		updateGraph();
	}
	
	public IMathExpression getExpression()
	{
		return expression;
	}
	
	public MathData getMathData()
	{
		return getGraph().getMathData();
	}
	
	public GraphComponent getGraph()
	{
		return graph;
	}
	
	public PanelComponent getInputPanel()
	{
		return inputPanel;
	}
	
	public LabelComponent getEquationLabel()
	{
		return equationLabel;
	}
	
	public void updateGraph()
	{
		redraw();
	}
	
	public static class ModelVariable
	{
		private final String variable;
		private final double def;
		
		public ModelVariable(String variable)
		{
			this(variable, 0);
		}
		
		public ModelVariable(String variable, double def)
		{
			this.variable = variable;
			this.def = def;
		}
		
		public String getVariable()
		{
			return variable;
		}
		
		public double getDefault()
		{
			return def;
		}
	}
}
