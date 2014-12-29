package rchs.tsa.math.interpreter.sequence;

import rchs.tsa.math.expression.OperatorType;
import rchs.tsa.math.sequence.ExpressionTokenType;
import net.anasa.util.Checks;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;

public class OperatorResolver implements ITypeResolver<OperatorType>
{
	@Override
	public ExpressionTokenType getType()
	{
		return ExpressionTokenType.OPERATOR;
	}
	
	@Override
	public OperatorType resolve(IToken item) throws ResolverException
	{
		return Checks.checkNotNull(OperatorType.get(item.getData()), new ResolverException("Invalid operator: " + item.getData()));
	}
}
