package rchs.tsa.math.resource.module.internal.ui;

import net.anasa.util.Debug;
import net.anasa.util.StringHelper;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.math.MathHelper;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.expression.MathData.AngleMode;
import rchs.tsa.math.expression.MathNumber;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.module.AbstractModule;
import rchs.tsa.math.resource.module.IModuleDelegate;
import rchs.tsa.math.system.Axis;
import rchs.tsa.math.system.NumberSystem;
import rchs.tsa.math.util.Evaluator;

public class MathModule extends AbstractModule implements IModuleDelegate
{
	public MathModule()
	{
		super("math_module", new Version(1, 0, 0), "Math Module", "Provides math evaluation ");
	}
	
	@Override
	public void setupMathData(MathData data, Properties props)
	{
		data.addFunction("abs", (n) -> Math.abs(n));
		data.addFunction("sqrt", (n) -> Math.sqrt(n));
		data.addFunction("cbrt", (n) -> Math.cbrt(n));
		data.addFunction("sin", (n) -> Math.sin(data.getMode().toRadians(n)));
		data.addFunction("cos", (n) -> Math.cos(data.getMode().toRadians(n)));
		data.addFunction("tan", (n) -> Math.tan(data.getMode().toRadians(n)));
		data.addFunction("asin", (n) -> Math.asin(data.getMode().toRadians(n)));
		data.addFunction("acos", (n) -> Math.acos(data.getMode().toRadians(n)));
		data.addFunction("atan", (n) -> Math.atan(data.getMode().toRadians(n)));
		data.addFunction("csc", (n) -> 1 / Math.sin(data.getMode().toRadians(n)));
		data.addFunction("sec", (n) -> 1 / Math.cos(data.getMode().toRadians(n)));
		data.addFunction("cot", (n) -> 1 / Math.tan(data.getMode().toRadians(n)));
		data.addFunction("floor", (n) -> Math.floor(n));
		data.addFunction("ceil", (n) -> Math.ceil(n));
		data.addFunction("log", (n) -> Math.log10(n));
		data.addFunction("ln", (n) -> Math.log(n));
		data.addFunction("fact", (n) -> MathHelper.factorial((int)n));
		
		data.addOperator('+', 1, (a, b) -> a + b);
		data.addOperator('-', 1, (a, b) -> a - b);
		data.addOperator('*', 2, (a, b) -> a * b);
		data.addOperator('/', 2, (a, b) -> a / b);
		data.addOperator('%', 2, (a, b) -> a % b);
		data.addOperator('^', 3, (a, b) -> Math.pow(a, b));
		
		data.addConstant(Math.PI, "pi", "\u03C0");
		data.addConstant(Math.E, "e");
		
		try
		{
			data.setMode(props.getEnum("mode", AngleMode.RADIANS));
		}
		catch(FormatException e)
		{
			Debug.err("Invalid angle mode: " + props.getString("mode", null));
		}
		
		for(Properties inner : props.getInnerList("variables._"))
		{
			try
			{
				data.setVariable(inner.getString("key", inner.getValue()), Evaluator.parse(data, inner.getString("value")).evaluate(data));
			}
			catch(MathException | FormatException e)
			{
				Debug.err("Failed to set variable from properties: " + inner + " (" + e + ")");
			}
		}
		
		for(Properties inner : props.getInnerList("functions._"))
		{
			try
			{
				IMathExpression expression = Evaluator.parse(data, inner.getString("expression"));
				NumberSystem system = new NumberSystem(new Axis(inner.getString("var", "x")));
				data.addFunction(inner.getString("name", inner.getValue()), (x) -> system.evaluate(expression, data, new MathNumber(x)).getValue());
			}
			catch(MathException | FormatException e)
			{
				Debug.err("Failed to create function from properties: " + inner + " (" + e + ")");
			}
		}
		
		for(Properties inner : props.getInnerList("operators._"))
		{
			try
			{
				IMathExpression expression = Evaluator.parse(data, inner.getString("expression"));
				NumberSystem system = new NumberSystem(new Axis(inner.getString("first", "a")), new Axis(inner.getString("second", "b")));
				data.addOperator(IFormat.CHAR.getFrom(inner.getString("signature")), inner.getInt("priority"), (a, b) -> system.evaluate(expression, data, new MathNumber(a), new MathNumber(b)).getValue());
			}
			catch(MathException | FormatException e)
			{
				Debug.err("Failed to create operator from properties: " + inner + " (" + e + ")");
			}
		}
		
		for(Properties inner : props.getInnerList("constants._"))
		{
			try
			{
				String[] names = StringHelper.split(inner.getString("names", inner.getString("name", inner.getValue())), ",|;", (s) -> s.trim());
				data.addConstant(inner.getDouble("value"), names);
			}
			catch(FormatException e)
			{
				Debug.err("Failed to create constant from properties: " + inner + " (" + e + ")");
			}
		}
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return this;
	}
}
