package rchs.tsa.math.ui;

import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.TextFieldComponent;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.util.Evaluator;

public class EvaluationComponent extends PanelComponent
{
	private final MathData mathData;
	
	private final CalculationComponent<String> calc;
	
	public EvaluationComponent(MathData mathData)
	{
		this.mathData = mathData;
		
		calc = new CalculationComponent<>("Evaluate", new TextFieldComponent(), (data) -> {
			try
			{
				return Evaluator.parse(mathData, data).evaluate(getMathData());
			}
			catch(MathException e)
			{
				return null;
			}
		});
		setComponent(calc, null);
	}

	public MathData getMathData()
	{
		return mathData;
	}
}
