package net.anasa.math.interpreter.sequence;

import net.anasa.math.sequence.TokenType;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;

public class TypeResolver implements ITypeResolver<IToken>
{
	private final TokenType type;
	
	public TypeResolver(TokenType type)
	{
		this.type = type;
	}
	
	@Override
	public TokenType getType()
	{
		return type;
	}
	
	@Override
	public IToken resolve(IToken item) throws ResolverException
	{
		return item;
	}
}
