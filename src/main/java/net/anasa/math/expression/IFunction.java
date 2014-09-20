package net.anasa.math.expression;

import net.anasa.math.MathData;
import net.anasa.math.MathNumber;

public interface IFunction
{
	public String getName();
	
	public MathNumber evaluate(MathNumber number, MathData data);
}
