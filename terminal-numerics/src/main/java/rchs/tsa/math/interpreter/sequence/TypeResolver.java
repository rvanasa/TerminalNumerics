package rchs.tsa.math.interpreter.sequence;

import rchs.tsa.math.sequence.ExpressionTokenType;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;

public class TypeResolver implements ITypeResolver<IToken>
{
	private final ExpressionTokenType type;
	
	public TypeResolver(ExpressionTokenType type)
	{
		this.type = type;
	}
	
	@Override
	public ExpressionTokenType getType()
	{
		return type;
	}
	
	@Override
	public IToken resolve(IToken item) throws ResolverException
	{
		return item;
	}
}
