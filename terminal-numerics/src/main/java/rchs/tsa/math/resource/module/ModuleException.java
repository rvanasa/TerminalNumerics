package rchs.tsa.math.resource.module;

public class ModuleException extends Exception
{
	public ModuleException(String message)
	{
		super(message);
	}
	
	public ModuleException(Exception e)
	{
		super(e);
	}
	
	public ModuleException(String message, Exception e)
	{
		super(message, e);
	}
}
