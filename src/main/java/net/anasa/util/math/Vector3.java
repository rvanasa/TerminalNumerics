package net.anasa.util.math;

public class Vector3 implements IVector<Vector3>
{
	private final double x, y, z;
	
	public Vector3()
	{
		this(0, 0, 0);
	}
	
	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector2 xz, double y)
	{
		this(xz.getX(), y, xz.getY());
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	@Override
	public double getMagnitude()
	{
		return distance(new Vector3());
	}
	
	@Override
	public Vector3 add(Vector3 vector)
	{
		return new Vector3(getX() + vector.getX(), getY() + vector.getY(), getZ() + vector.getZ());
	}
	
	@Override
	public Vector3 subtract(Vector3 vector)
	{
		return add(vector.getNegative());
	}
	
	@Override
	public Vector3 multiply(double scale)
	{
		return new Vector3(getX() * scale, getY() * scale, getZ() * scale);
	}
	
	@Override
	public Vector3 normalize()
	{
		double magnitude = getMagnitude();
		return divide(magnitude);
	}
	
	@Override
	public double distanceSq(Vector3 pos)
	{
		return Math.pow(getX() - pos.getX(), 2) * Math.pow(getY() - pos.getY(), 2) + Math.pow(getZ() - pos.getZ(), 2);
	}
	
	@Override
	public double distance(Vector3 pos)
	{
		return Math.sqrt(distanceSq(pos));
	}
	
	public boolean equals(Vector3 vector)
	{
		return getX() == vector.getX() && getY() == vector.getY() && getZ() == vector.getZ();
	}
	
	@Override
	public String toString()
	{
		return "<" + getX() + ", " + getY() + ", " + getZ() + ">";
	}
}
