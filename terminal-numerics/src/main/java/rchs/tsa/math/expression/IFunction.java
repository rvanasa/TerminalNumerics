package rchs.tsa.math.expression;

public interface IFunction
{
	public String getName();
	
	public INumber evaluate(INumber number, MathData data);
}
