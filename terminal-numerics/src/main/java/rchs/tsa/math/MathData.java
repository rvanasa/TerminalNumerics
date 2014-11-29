package rchs.tsa.math;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MathData
{
	private final HashMap<String, MathNumber> variables = new LinkedHashMap<>();
	
	private boolean degrees = false;
	
	public MathData()
	{
		
	}

	public boolean hasVariable(String key)
	{
		return variables.containsKey(key);
	}
	
	public MathNumber getVariable(String key)
	{
		return variables.get(key);
	}
	
	public void setVariable(String key, MathNumber value)
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
