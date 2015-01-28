package rchs.tsa.math.expression;

public class NumberExpression extends MathExpression
{
	private final INumber number;
	
	public NumberExpression(double number)
	{
		this(new MathNumber(number));
	}
	
	public NumberExpression(INumber number)
	{
		this.number = number;
	}
	
	public INumber getNumber()
	{
		return number;
	}
	
	@Override
	public INumber evaluate(MathData data)
	{
		return getNumber();
	}
	
	@Override
	public String getStringValue()
	{
		return getNumber().toString();
	}
	
	@Override
	public IMathExpression[] getChildren()
	{
		return new IMathExpression[] {};
	}
}
