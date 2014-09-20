package net.anasa.math.interpreter.sequence;

import net.anasa.math.expression.OperatorType;
import net.anasa.math.sequence.SequenceToken;
import net.anasa.math.sequence.SequenceToken.TokenType;
import net.anasa.util.Checks;
import net.anasa.util.resolver.ResolverException;

public class OperatorResolver implements ITypeResolver<OperatorType>
{
	@Override
	public TokenType getType()
	{
		return TokenType.OPERATOR;
	}
	
	@Override
	public OperatorType resolve(SequenceToken item) throws ResolverException
	{
		return Checks.checkNotNull(OperatorType.get(item.getData()), new ResolverException("Invalid operator: " + item.getData()));
	}
}
