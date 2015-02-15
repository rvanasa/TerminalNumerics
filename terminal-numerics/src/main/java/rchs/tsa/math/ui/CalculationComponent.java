package rchs.tsa.math.ui;

import net.anasa.util.StringHelper;
import net.anasa.util.ui.IInputComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.TextFieldComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import rchs.tsa.math.MathException;

public class CalculationComponent<T> extends PanelComponent
{
	private final PropertyFieldComponent<T> property;
	
	private final TextFieldComponent output;
	
	private final ICalculationCallback<T> callback;
	
	public CalculationComponent(String name, IInputComponent<T> input, ICalculationCallback<T> callback)
	{
		property = new PropertyFieldComponent<>(name, input);
		
		output = new TextFieldComponent(12);
		output.setEditable(false);
		
		this.callback = callback;
		
		input.addActionListener(this::calculate);
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.LEFT, new PanelComponent(property));
		layout.set(BorderPosition.RIGHT, new PanelComponent(output));
		layout.apply(this);
	}
	
	public PropertyFieldComponent<T> getProperty()
	{
		return property;
	}
	
	public TextFieldComponent getOutput()
	{
		return output;
	}
	
	public ICalculationCallback<T> getCallback()
	{
		return callback;
	}
	
	public void calculate()
	{
		try
		{
			getOutput().setValue(StringHelper.getOrNull(getCallback().calculate(getProperty().getValue())));
		}
		catch(MathException e)
		{
			getOutput().setValue(null);
		}
	}
	
	public interface ICalculationCallback<T>
	{
		public Object calculate(T input) throws MathException;
	}
}
