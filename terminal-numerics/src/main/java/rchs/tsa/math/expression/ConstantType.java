package rchs.tsa.math.expression;

import net.anasa.util.Listing;

public enum ConstantType
{
	PI(Math.PI, "\u03C0"),
	E(Math.E);
	
	private final double value;
	
	private final String[] otherNames;
	
	private ConstantType(double value, String... otherNames)
	{
		this.value = value;
		
		this.otherNames = otherNames;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public String getName()
	{
		return name().toLowerCase();
	}
	
	public String[] getOtherNames()
	{
		return otherNames;
	}
	
	public boolean isName(String name)
	{
		return getName().equalsIgnoreCase(name) || new Listing<>(getOtherNames()).contains((n) -> n.equalsIgnoreCase(name));
	}
	
	public static boolean isConstant(String name)
	{
		return get(name) != null;
	}
	
	public static ConstantType get(String name)
	{
		for(ConstantType constant : values())
		{
			if(constant.isName(name))
			{
				return constant;
			}
		}
		
		return null;
	}
}
