package rchs.tsa.math.graph;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IExpression;
import rchs.tsa.math.expression.INumber;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.expression.MathNumber;

public class Graph
{
	private final IExpression expression;
	private final Axis axis;
	
	private final IConstraint[] constraints;
	
	private final MathData data = new MathData();
	
	public Graph(IExpression expression, IConstraint... constraints)
	{
		this(expression, new Axis("x"), constraints);
	}
	
	public Graph(IExpression expression, Axis axis, IConstraint... constraints)
	{
		this.expression = expression;
		this.axis = axis;
		
		this.constraints = constraints;
	}
	
	public IExpression getExpression()
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
	
	public MathData getData()
	{
		return data;
	}
	
	public INumber getFrom(INumber x) throws MathException
	{
		getData().setVariable(getAxis().getKey(), x);
		
		INumber y = getExpression().evaluate(getData());
		
		for(IConstraint constraint : getConstraints())
		{
			if(constraint.isValid(x, y))
			{
				return MathNumber.NaN;
			}
		}
		
		return y;
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
