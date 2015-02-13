package rchs.tsa.math.expression;

import java.util.function.BiFunction;

public enum FunctionType implements IFunction
{
	ABS((n, data) -> Math.abs(n)),
	SQRT((n, data) -> Math.sqrt(n)),
	CBRT((n, data) -> Math.cbrt(n)),
	SIN((n, data) -> Math.sin(data.isDegreeMode() ? Math.toRadians(n) : n)),
	ASIN((n, data) -> data.isDegreeMode() ? Math.toDegrees(Math.asin(n)) : Math.asin(n)),
	CSC((n, data) -> 1 / Math.sin(data.isDegreeMode() ? Math.toRadians(n) : n)),
	COS((n, data) -> Math.cos(data.isDegreeMode() ? Math.toRadians(n) : n)),
	ACOS((n, data) -> data.isDegreeMode() ? Math.toDegrees(Math.acos(n)) : Math.acos(n)),
	SEC((n, data) -> 1 / Math.cos(data.isDegreeMode() ? Math.toRadians(n) : n)),
	TAN((n, data) -> Math.tan(data.isDegreeMode() ? Math.toRadians(n) : n)),
	ATAN((n, data) -> data.isDegreeMode() ? Math.toDegrees(Math.atan(n)) : Math.atan(n)),
	COT((n, data) -> 1 / Math.tan(data.isDegreeMode() ? Math.toRadians(n) : n)),
	FLOOR((n, data) -> Math.floor(n)),
	CEIL((n, data) -> Math.ceil(n)),
	LOG((n, data) -> Math.log10(n)),
	LN((n, data) -> Math.log(n));
	
	private final BiFunction<Double, MathData, Double> handle;
	
	private FunctionType(BiFunction<Double, MathData, Double> handle)
	{
		this.handle = handle;
	}
	
	@Override
	public String getName()
	{
		return name().toLowerCase();
	}
	
	@Override
	public INumber evaluate(INumber number, MathData data)
	{
		return new MathNumber(handle.apply(number.getValue(), data));
	}
	
	public static boolean isFunction(String name)
	{
		return get(name) != null;
	}
	
	public static FunctionType get(String name)
	{
		for(FunctionType type : FunctionType.values())
		{
			if(type.getName().equals(name))
			{
				return type;
			}
		}
		
		return null;
	}
}
