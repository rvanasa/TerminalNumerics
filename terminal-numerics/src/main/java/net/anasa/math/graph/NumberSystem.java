package net.anasa.math.graph;

import net.anasa.math.MathNumber;
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
		private final MathNumber[] coords;
		
		public Coordinate(NumberSystem system, MathNumber... coords)
		{
			this.coords = new MathNumber[getDimensions()];
			
			for(int i = 0; i < coords.length; i++)
			{
				set(i, coords[i]);
			}
		}
		
		public MathNumber[] getCoords()
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
		
		public MathNumber get(int dimension)
		{
			return getCoords()[dimension];
		}
		
		public void set(int dimension, MathNumber number)
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
