package rchs.tsa.math.ui;

import java.util.Arrays;

import net.anasa.util.Listing;
import net.anasa.util.logic.statement.ForEachStatement;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.UI;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIFlowLayout;
import net.anasa.util.ui.layout.UIFlowLayout.FlowType;
import net.anasa.util.ui.layout.UIVerticalLayout;
import net.anasa.util.ui.menu.CheckBoxComponent;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.expression.MathData.AngleMode;
import rchs.tsa.math.expression.MathNumber;

public class MathDataComponent extends PanelComponent
{
	private final MathData data;
	
	private final PanelComponent variables;
	
	public MathDataComponent(MathData data)
	{
		this.data = data;
		
		variables = new PanelComponent();
		variables.setBorder(4, 4);
		
		new UIBorderLayout(4)
				.set(BorderPosition.TOP, new ScrollComponent(320, 140, new PanelComponent(new UIBorderLayout()
						.set(BorderPosition.TOP, variables))))
				.set(BorderPosition.BOTTOM, new PanelComponent(new UIFlowLayout(FlowType.RIGHT)
						.add(new ButtonComponent("Add Variable(s)", () -> UI.input("Add Variable(s)", "Enter variable name(s):", (vars) -> new ForEachStatement<>(Arrays.asList(vars.split(",|;")), (var) -> addVariable(var.trim())).run())))
						.add(new CheckBoxComponent("Degree Mode", data.getMode() == AngleMode.DEGREES, (value) -> {
							getData().setMode(value ? AngleMode.DEGREES : AngleMode.RADIANS);
							updateVariables();
						}))
						))
				.apply(this);
	}
	
	public MathData getData()
	{
		return data;
	}
	
	public PanelComponent getVariableComponent()
	{
		return variables;
	}
	
	public void addVariable(String variable)
	{
		getData().setVariable(variable, new MathNumber(0));
		updateVariables();
	}
	
	public void updateVariables()
	{
		Listing<VariableComponent> components = new Listing<>();
		
		getVariableComponent().removeComponents();
		UIVerticalLayout layout = new UIVerticalLayout(4);
		for(String var : getData().getVariables().getKeys())
		{
			VariableComponent component = new VariableComponent(getData(), var);
			component.getInputField().addKeyListener((event) -> {
				for(VariableComponent c : components)
				{
					c.updateData();
				}
				redraw();
			});
			components.add(component);
			layout.add(component);
		}
		layout.apply(getVariableComponent());
		
		revalidate();
		redraw();
	}
}
