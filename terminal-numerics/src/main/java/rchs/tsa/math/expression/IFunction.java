package rchs.tsa.math.expression;

import rchs.tsa.math.MathData;
import rchs.tsa.math.MathNumber;

public interface IFunction
{
	public String getName();
	
	public MathNumber evaluate(MathNumber number, MathData data);
}
