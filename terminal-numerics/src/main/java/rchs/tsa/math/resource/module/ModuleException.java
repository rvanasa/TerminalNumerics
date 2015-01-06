package rchs.tsa.math.resource.module;

public class ModuleException extends Exception
{
	public ModuleException(String message)
	{
		super(message);
	}
	
	public ModuleException(Throwable e)
	{
		super(e);
	}
	
	public ModuleException(String message, Throwable e)
	{
		super(message, e);
	}
}
