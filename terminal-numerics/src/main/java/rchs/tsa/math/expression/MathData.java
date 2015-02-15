package rchs.tsa.math.expression;

import java.util.HashMap;
import java.util.Map;

public class MathData
{
	private final Map<String, INumber> variables = new HashMap<>();
	
	private boolean degrees = false;
	
	public MathData()
	{
		
	}
	
	public boolean isValidVariable(String key)
	{
		return key != null;
	}
	
	public Map<String, INumber> getVariables()
	{
		return variables;
	}

	public boolean hasVariable(String key)
	{
		return getVariables().containsKey(key);
	}
	
	public INumber getVariable(String key)
	{
		return getVariables().get(key);
	}
	
	public void setVariable(String key, INumber value)
	{
		if(isValidVariable(key))
		{
			getVariables().put(key, value);
		}
	}
	
	public void removeVariable(String key)
	{
		getVariables().remove(key);
	}
	
	public void clearVariables()
	{
		getVariables().clear();
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
