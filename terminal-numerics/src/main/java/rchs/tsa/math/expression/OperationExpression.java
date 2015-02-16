package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public class OperationExpression extends MathExpression
{
	private final IOperator operator;
	private final IMathExpression a, b;
	
	public OperationExpression(IOperator operator, IMathExpression a, IMathExpression b)
	{
		this.operator = operator;
		
		this.a = a;
		this.b = b;
	}

	public IOperator getOperator()
	{
		return operator;
	}

	public IMathExpression getA()
	{
		return a;
	}

	public IMathExpression getB()
	{
		return b;
	}

	@Override
	public INumber evaluate(MathData data) throws MathException
	{
		return getOperator().operate(getA().evaluate(data), getB().evaluate(data));
	}

	@Override
	public String getStringValue()
	{
		return getDisplayData(getA()) + getOperator() + getDisplayData(getB());
	}
	
	private String getDisplayData(IMathExpression expression)
	{
		if(expression instanceof OperationExpression && getOperator().hasPriority(((OperationExpression)expression).getOperator()))
		{
			return "(" + expression + ")";
		}
		
		return expression.toString();
	}
	
	@Override
	public IMathExpression[] getChildren()
	{
		return new IMathExpression[] {getA(), getB()};
	}
}
