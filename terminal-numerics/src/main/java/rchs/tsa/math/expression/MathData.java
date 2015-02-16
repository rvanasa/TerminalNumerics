package rchs.tsa.math.expression;

import net.anasa.util.Listing;
import net.anasa.util.Mapping;
import net.anasa.util.NumberHelper;
import net.anasa.util.StringHelper;
import net.anasa.util.data.properties.Properties;
import rchs.tsa.math.expression.MathFunction.IMathFunctionHandle;
import rchs.tsa.math.expression.MathOperator.IMathOperatorHandle;
import rchs.tsa.math.resource.module.IModule;
import rchs.tsa.math.resource.module.context.ModuleContext;

public class MathData
{
	public static MathData create(ModuleContext context, Properties config)
	{
		MathData data = new MathData(config);
		
		for(IModule module : context.getModules())
		{
			module.getDelegate().setupMathData(data, config);
		}
		
		return data;
	}
	
	private final Properties config;
	
	private final Listing<IOperator> operators = new Listing<>();
	private final Listing<IFunction> functions = new Listing<>();
	private final Listing<IConstant> constants = new Listing<>();
	
	private final Mapping<String, INumber> variables = new Mapping<>();
	
	private AngleMode mode = AngleMode.RADIANS;
	
	private MathData(Properties config)
	{
		this.config = config;
	}
	
	public Properties getConfig()
	{
		return config;
	}
	
	public Listing<IOperator> getOperators()
	{
		return operators;
	}
	
	public boolean isOperator(String signature)
	{
		return getOperator(signature) != null;
	}
	
	public IOperator getOperator(String signature)
	{
		return getOperators().getFirst((operator) -> StringHelper.equals(operator.getSignature(), signature));
	}
	
	public void addOperator(IOperator operator)
	{
		getOperators().add(operator);
	}
	
	public void addOperator(char signature, int priority, IMathOperatorHandle operator)
	{
		addOperator(new MathOperator(signature, priority, operator));
	}
	
	public Listing<IFunction> getFunctions()
	{
		return functions;
	}
	
	public boolean isFunction(String name)
	{
		return getFunction(name) != null;
	}
	
	public IFunction getFunction(String name)
	{
		return getFunctions().getFirst((function) -> StringHelper.equals(function.getName(), name));
	}
	
	public void addFunction(IFunction function)
	{
		getFunctions().add(function);
	}
	
	public void addFunction(String id, IMathFunctionHandle function)
	{
		addFunction(new MathFunction(id, function));
	}
	
	public Listing<IConstant> getConstants()
	{
		return constants;
	}
	
	public boolean isConstant(String name)
	{
		return getConstant(name) != null;
	}
	
	public IConstant getConstant(String name)
	{
		return getConstants().getFirst((constant) -> constant.isReferring(name));
	}
	
	public void addConstant(IConstant constant)
	{
		getConstants().add(constant);
	}
	
	public void addConstant(double value, String... names)
	{
		addConstant(new MathConstant(value, names));
	}
	
	public boolean isNumber(String data)
	{
		return NumberHelper.isDouble(data) || isConstant(data);
	}
	
	public boolean isValidVariable(String key)
	{
		return key != null && !isConstant(key);
	}
	
	public Mapping<String, INumber> getVariables()
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
	
	public AngleMode getMode()
	{
		return mode;
	}
	
	public void setMode(AngleMode mode)
	{
		this.mode = mode;
	}
	
	public enum AngleMode
	{
		RADIANS(1),
		DEGREES(Math.PI / 180);
		
		private final double factor;
		
		private AngleMode(double factor)
		{
			this.factor = factor;
		}
		
		public double toRadians(double n)
		{
			return n * factor;
		}
	}
}
