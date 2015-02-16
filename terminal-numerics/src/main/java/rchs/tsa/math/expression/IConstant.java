package rchs.tsa.math.expression;

public interface IConstant
{
	public boolean isReferring(String name);
	
	public INumber getValue();
}
