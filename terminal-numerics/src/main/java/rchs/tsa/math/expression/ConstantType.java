package rchs.tsa.math.expression;

import net.anasa.util.Listing;

public enum ConstantType
{
	PI(Math.PI, "pi", "\u03C0"),
	E(Math.E, "e");
	
	private final double value;
	
	private final String[] names;
	
	private ConstantType(double value, String... otherNames)
	{
		this.value = value;
		
		this.names = otherNames;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public String[] getNames()
	{
		return names;
	}
	
	public boolean isName(String name)
	{
		return new Listing<>(getNames()).contains((n) -> n.equals(name));
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
