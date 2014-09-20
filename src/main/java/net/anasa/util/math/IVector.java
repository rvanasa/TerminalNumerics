package net.anasa.util.math;

public interface IVector<T>
{
	public double getMagnitude();
	
	public T add(T vector);

	public T subtract(T vector);

	public T multiply(double scale);
	
	public default T divide(double scale)
	{
		return multiply(1 / scale);
	}
	
	public double distanceSq(T vector);
	
	public default double distance(T vector)
	{
		return Math.sqrt(distanceSq(vector));
	}
	
	public default T getNegative()
	{
		return multiply(-1);
	}
	
	public T normalize();
}
