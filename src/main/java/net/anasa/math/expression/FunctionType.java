package net.anasa.math.expression;

import net.anasa.math.MathData;
import net.anasa.math.MathNumber;

public enum FunctionType implements IFunction
{
	ABS((n, data) -> Math.abs(n)),
	SQRT((n, data) -> Math.sqrt(n)),
	CBRT((n, data) -> Math.cbrt(n)),
	SIN((n, data) -> Math.sin(data.isDegreeMode() ? Math.toRadians(n) : n)),
	ASIN((n, data) -> data.isDegreeMode() ? Math.toDegrees(Math.asin(n)) : Math.asin(n)),
	COS((n, data) -> Math.cos(data.isDegreeMode() ? Math.toRadians(n) : n)),
	ACOS((n, data) -> data.isDegreeMode() ? Math.toDegrees(Math.acos(n)) : Math.acos(n)),
	TAN((n, data) -> Math.tan(data.isDegreeMode() ? Math.toRadians(n) : n)),
	ATAN((n, data) ->data.isDegreeMode() ? Math.toDegrees(Math.atan(n)) : Math.atan(n)),
	FLOOR((n, data) -> Math.floor(n)),
	CEIL((n, data) -> Math.ceil(n)),
	LOG((n, data) -> Math.log10(n)),
	LN((n, data) -> Math.log(n));
	
	private final FunctionTypeHandle handle;
	
	private FunctionType(FunctionTypeHandle handle)
	{
		this.handle = handle;
	}
	
	@Override
	public String getName()
	{
		return name().toLowerCase();
	}
	
	@Override
	public MathNumber evaluate(MathNumber number, MathData data)
	{
		return new MathNumber(handle.evaluate(number.getValue(), data));
	}
	
	private interface FunctionTypeHandle
	{
		public double evaluate(double number, MathData data);
	}
	
	public static boolean isFunction(String name)
	{
		return get(name) != null;
	}
	
	public static FunctionType get(String name)
	{
		for(FunctionType type : FunctionType.values())
		{
			if(type.getName().equalsIgnoreCase(name))
			{
				return type;
			}
		}
		
		return null;
	}
}
