package net.anasa.util.resolver;

public class ResolverException extends Exception
{
	public ResolverException(String message)
	{
		super(message);
	}
	
	public ResolverException(Throwable e)
	{
		super(e);
	}
	
	public ResolverException(String message, Throwable e)
	{
		super(message, e);
	}
}
