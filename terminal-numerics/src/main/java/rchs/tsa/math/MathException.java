package rchs.tsa.math;

public class MathException extends Exception
{
	public MathException(String message)
	{
		super(message);
	}
	
	public MathException(Exception e)
	{
		super(e);
	}
	
	public MathException(String message, Exception e)
	{
		super(message, e);
	}
}
