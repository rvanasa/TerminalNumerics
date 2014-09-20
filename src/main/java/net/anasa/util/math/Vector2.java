package net.anasa.util.math;

public class Vector2 implements IVector<Vector2>
{
	private final double x, y;
	
	public Vector2()
	{
		this(0, 0);
	}
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	@Override
	public double getMagnitude()
	{
		return distance(new Vector2());
	}
	
	public double getAngle()
	{
		return Math.atan2(getY(), getX());
	}
	
	@Override
	public Vector2 add(Vector2 vector)
	{
		return new Vector2(getX() + vector.getX(), getY() + vector.getY());
	}
	
	@Override
	public Vector2 subtract(Vector2 vector)
	{
		return new Vector2(getX() - vector.getX(), getY() - vector.getY());
	}
	
	@Override
	public Vector2 multiply(double scale)
	{
		return new Vector2(getX() * scale, getY() * scale);
	}
	
	@Override
	public Vector2 normalize()
	{
		return null;
	}
	
	@Override
	public double distanceSq(Vector2 vector)
	{
		return Math.pow(getX() - vector.getX(), 2) + Math.pow(getY() - vector.getY(), 2);
	}
	
	public Vector2 rotate(double angle)
	{
		double x = getX() * Math.cos(angle) - getY() * Math.sin(angle);
		double y = getX() * Math.sin(angle) + getY() * Math.cos(angle);
		
		return new Vector2(x, y);
	}
	
	public Vector2 rotateAround(Vector2 center, double angle)
	{
		double x = center.getX() + (getX() - center.getX()) * Math.cos(angle) - (getY() - center.getY()) * Math.sin(angle);
		double y = center.getY() + (getX() - center.getX()) * Math.sin(angle) + (getY() - center.getY()) * Math.cos(angle);
		
		return new Vector2(x, y);
	}
	
	public boolean equals(Vector2 vector)
	{
		return getX() == vector.getX() && getY() == vector.getY();
	}
	
	@Override
	public String toString()
	{
		return "<" + getX() + ", " + getY() + ">";
	}
}
