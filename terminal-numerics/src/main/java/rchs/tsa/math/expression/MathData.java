package rchs.tsa.math.expression;

import java.util.LinkedHashMap;
import java.util.Map;

public class MathData
{
	private final Map<String, INumber> variables = new LinkedHashMap<>();
	
	private boolean degrees = false;
	
	public MathData()
	{
		
	}

	public boolean hasVariable(String key)
	{
		return variables.containsKey(key);
	}
	
	public INumber getVariable(String key)
	{
		return variables.get(key);
	}
	
	public void setVariable(String key, INumber value)
	{
		variables.put(key, value);
	}
	
	public void clearVariables()
	{
		variables.clear();
	}

	public boolean isDegreeMode()
	{
		return degrees;
	}

	public void setDegreeMode(boolean degrees)
	{
		this.degrees = degrees;
	}
}
