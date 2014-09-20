package net.anasa.math.ui;

import net.anasa.math.MathData;
import net.anasa.math.MathException;
import net.anasa.math.util.Evaluator;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.TextFieldComponent;

public class EvaluationComponent extends PanelComponent
{
	private final MathData mathData;
	
	private final CalculationComponent<String> calc;
	
	public EvaluationComponent()
	{
		this(new MathData());
	}
	
	public EvaluationComponent(MathData mathData)
	{
		this.mathData = mathData;
		
		calc = new CalculationComponent<>("Evaluate", new TextFieldComponent(), (data) -> {
			try
			{
				return Evaluator.evaluate(data).evaluate(getMathData());
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
