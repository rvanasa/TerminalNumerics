package rchs.tsa.math.interpreter.sequence;

import net.anasa.util.Checks;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;
import rchs.tsa.math.expression.IOperator;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.sequence.ExpressionTokenType;

public class OperatorResolver implements ITypeResolver<IOperator>
{
	private final MathData mathData;
	
	public OperatorResolver(MathData mathData)
	{
		this.mathData = mathData;
	}
	
	public MathData getMathData()
	{
		return mathData;
	}
	
	@Override
	public ExpressionTokenType getType()
	{
		return ExpressionTokenType.OPERATOR;
	}

	@Override
	public IOperator resolve(IToken item) throws ResolverException
	{
		return Checks.checkNotNull(getMathData().getOperator(item.getData()), new ResolverException("Invalid operator: " + item.getData()));
	}
}
