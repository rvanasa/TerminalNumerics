package rchs.tsa.math.expression;


public interface IFunction
{
	public String getName();
	
	public MathNumber evaluate(MathNumber number, MathData data);
}
