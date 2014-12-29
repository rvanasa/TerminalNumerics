package rchs.tsa.math.expression;


public class NumberExpression extends MathExpression
{
	private final MathNumber number;
	
	public NumberExpression(double number)
	{
		this(new MathNumber(number));
	}
	
	public NumberExpression(MathNumber number)
	{
		this.number = number;
	}
	
	public MathNumber getNumber()
	{
		return number;
	}

	@Override
	public MathNumber evaluate(MathData data)
	{
		return getNumber();
	}

	@Override
	public String getStringValue()
	{
		return getNumber().toString();
	}
	
	@Override
	public IExpression[] getChildren()
	{
		return new IExpression[] {};
	}
}
