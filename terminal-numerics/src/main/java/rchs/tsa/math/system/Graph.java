package rchs.tsa.math.system;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.INumber;
import rchs.tsa.math.expression.MathData;

public class Graph
{
	private final IMathExpression expression;
	private final Axis axis;
	
	private final IConstraint[] constraints;
	
	public Graph(IMathExpression expression, IConstraint... constraints)
	{
		this(expression, new Axis("x"), constraints);
	}
	
	public Graph(IMathExpression expression, Axis axis, IConstraint... constraints)
	{
		this.expression = expression;
		this.axis = axis;
		
		this.constraints = constraints;
	}
	
	public IMathExpression getExpression()
	{
		return expression;
	}
	
	public Axis getAxis()
	{
		return axis;
	}
	
	public IConstraint[] getConstraints()
	{
		return constraints;
	}
	
	public INumber getFrom(INumber x, MathData data) throws MathException
	{
		try
		{
			data.setVariable(getAxis().getKey(), x);
			
			INumber y = getExpression().evaluate(data);
			
			for(IConstraint constraint : getConstraints())
			{
				if(!constraint.isValid(x, y))
				{
					return INumber.NaN;
				}
			}
			
			return y;
		}
		finally
		{
			data.removeVariable(getAxis().getKey());
		}
	}
	
	@Override
	public String toString()
	{
		return getExpression().getStringValue();
	}
	
	public interface IConstraint
	{
		public boolean isValid(INumber x, INumber y);
	}	
}
