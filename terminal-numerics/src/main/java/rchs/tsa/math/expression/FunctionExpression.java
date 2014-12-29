package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public class FunctionExpression extends MathExpression
{
	private final IFunction function;
	private final IExpression operand;
	
	public FunctionExpression(IFunction function, IExpression operand)
	{
		this.function = function;
		this.operand = operand;
	}
	
	public IFunction getFunction()
	{
		return function;
	}
	
	public IExpression getOperand()
	{
		return operand;
	}
	
	@Override
	public MathNumber evaluate(MathData data) throws MathException
	{
		return getFunction().evaluate(getOperand().evaluate(data), data);
	}
	
	@Override
	public String getStringValue()
	{
		return getFunction().getName() + "(" + getOperand() + ")";
	}
	
	@Override
	public IExpression[] getChildren()
	{
		return new IExpression[] {getOperand()};
	}
}
