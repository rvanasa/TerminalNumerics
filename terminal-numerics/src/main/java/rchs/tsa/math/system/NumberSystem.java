package rchs.tsa.math.system;

import net.anasa.util.ArrayHelper;
import net.anasa.util.Checks;
import net.anasa.util.StringHelper;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.INumber;
import rchs.tsa.math.expression.MathData;

public class NumberSystem
{
	private final Axis[] axes;
	
	public NumberSystem(Axis... axes)
	{
		this.axes = axes;
	}

	public Axis[] getAxes()
	{
		return axes;
	}

	public Axis getAxis(int index)
	{
		return ArrayHelper.getFromNullable(getAxes(), index);
	}
	
	public int getDimensions()
	{
		return getAxes().length;
	}
	
	public INumber evaluate(IMathExpression expression, MathData data, INumber... numbers) throws MathException
	{
		try
		{
			Checks.checkNotNull(numbers, new MathException("Numbers cannot be null"));
			Checks.check(numbers.length == getDimensions(), new MathException("Invalid dimension count: " + numbers.length + " (should be " + getDimensions() + ")"));
			
			for(int i = 0; i < getDimensions(); i++)
			{
				data.setVariable(getAxis(i).getKey(), numbers[i]);
			}
			
			return expression.evaluate(data);
		}
		finally
		{
			for(int i = 0; i < getDimensions(); i++)
			{
				data.removeVariable(getAxis(i).getKey());
			}
		}
	}
	
	public class Coordinate
	{
		private final INumber[] coords;
		
		public Coordinate(NumberSystem system, INumber... coords)
		{
			this.coords = new INumber[getDimensions()];
			
			for(int i = 0; i < coords.length; i++)
			{
				set(i, coords[i]);
			}
		}
		
		public INumber[] getCoords()
		{
			return coords;
		}
		
		public int getDimensions()
		{
			return NumberSystem.this.getDimensions();
		}
		
		public Axis getAxis(int dimension)
		{
			return NumberSystem.this.getAxes()[dimension];
		}
		
		public INumber get(int dimension)
		{
			return getCoords()[dimension];
		}
		
		public void set(int dimension, INumber number)
		{
			getCoords()[dimension] = number;
		}
		
		@Override
		public String toString()
		{
			return "(" + StringHelper.join(", ", getCoords()) + ")";
		}
	}
}
