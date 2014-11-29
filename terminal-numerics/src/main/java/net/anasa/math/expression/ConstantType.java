package net.anasa.math.expression;

public enum ConstantType
{
	PI(Math.PI),
	E(Math.E);
	
	private final double value;
	
	private ConstantType(double value)
	{
		this.value = value;
	}

	public double getValue()
	{
		return value;
	}
	
	public String getName()
	{
		return name().toLowerCase();
	}
	
	public static boolean isConstant(String name)
	{
		return get(name) != null;
	}
	
	public static ConstantType get(String name)
	{
		for(ConstantType constant : values())
		{
			if(constant.getName().equalsIgnoreCase(name))
			{
				return constant;
			}
		}
		
		return null;
	}
}
