package rchs.tsa.math.expression;

import rchs.tsa.math.MathException;

public class FunctionExpression extends MathExpression
{
	private final IFunction function;
	private final IMathExpression operand;
	
	public FunctionExpression(IFunction function, IMathExpression operand)
	{
		this.function = function;
		this.operand = operand;
	}
	
	public IFunction getFunction()
	{
		return function;
	}
	
	public IMathExpression getOperand()
	{
		return operand;
	}
	
	@Override
	public INumber evaluate(MathData data) throws MathException
	{
		return getFunction().evaluate(getOperand().evaluate(data));
	}
	
	@Override
	public String getStringValue()
	{
		return getFunction().getName() + "(" + getOperand() + ")";
	}
	
	@Override
	public IMathExpression[] getChildren()
	{
		return new IMathExpression[] {getOperand()};
	}
}
