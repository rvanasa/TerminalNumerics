package net.anasa.util;

public class Bounds
{
	private final double minX, minY, maxX, maxY;
	
	public Bounds(double x1, double y1, double x2, double y2)
	{
		this.minX = Math.min(x1, x2);
		this.minY = Math.min(y1, y2);
		this.maxX = Math.max(x1, x2);
		this.maxY = Math.max(y1, y2);
	}

	public double getMinX()
	{
		return minX;
	}

	public double getMinY()
	{
		return minY;
	}

	public double getMaxX()
	{
		return maxX;
	}

	public double getMaxY()
	{
		return maxY;
	}
	
	public double getWidth()
	{
		return getMaxX() - getMinX();
	}
	
	public double getHeight()
	{
		return getMaxY() - getMinY();
	}
	
	public Bounds translate(int x, int y)
	{
		return new Bounds(getMinX() + x, getMinY() + y, getMaxX() + x, getMaxY() + y);
	}
	
	@Override
	public String toString()
	{
		return "(" + StringHelper.join(", ", getMinX(), getMinY(), getMaxX(), getMaxY()) + ")";
	}
}
