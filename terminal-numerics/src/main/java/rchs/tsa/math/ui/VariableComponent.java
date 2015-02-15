package rchs.tsa.math.ui;

import java.awt.Font;

import net.anasa.util.Debug;
import net.anasa.util.Debug.MessageType;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.TextFieldComponent;
import net.anasa.util.ui.layout.UIGridLayout;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.INumber;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.util.Evaluator;

public class VariableComponent extends PanelComponent
{
	private final MathData data;
	private final String variable;
	
	private final TextFieldComponent inputField;
	private final LabelComponent valueLabel;
	
	public VariableComponent(MathData data, String variable)
	{
		this.data = data;
		this.variable = variable;
		
		this.inputField = new TextFieldComponent(16);
		inputField.addKeyListener((event) -> updateData());
		
		this.valueLabel = new LabelComponent();
		
		setBorder(2, 2);
		new UIGridLayout()
				.setPaddingX(34)
				.add(0, 0, new LabelComponent(variable, new Font(Font.SERIF, Font.BOLD, 14)))
				.add(1, 0, valueLabel)
				.add(2, 0, inputField)
				.apply(this);
		
		updateData();
	}
	
	public MathData getData()
	{
		return data;
	}
	
	public String getVariable()
	{
		return variable;
	}
	
	public TextFieldComponent getInputField()
	{
		return inputField;
	}
	
	public LabelComponent getValueLabel()
	{
		return valueLabel;
	}
	
	public void updateData()
	{
		INumber value = getData().getVariable(getVariable());
		
		try
		{
			value = Evaluator.parse(getInputField().getValue()).evaluate(getData());
		}
		catch(MathException e)
		{
			Debug.msg(MessageType.ERROR, e.getMessage());
		}
		
		getData().setVariable(getVariable(), value);
		
		getValueLabel().setText(getData().getVariable(getVariable()).getStringValue());
		redraw();
	}
}
