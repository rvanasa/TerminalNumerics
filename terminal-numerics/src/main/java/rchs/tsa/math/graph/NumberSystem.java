package rchs.tsa.math.graph;

import rchs.tsa.math.expression.INumber;
import net.anasa.util.StringHelper;

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
	
	public int getDimensions()
	{
		return getAxes().length;
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
