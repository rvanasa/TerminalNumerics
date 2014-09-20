package net.anasa.math.interpreter.sequence;

import net.anasa.math.sequence.SequenceToken;
import net.anasa.math.sequence.SequenceToken.TokenType;
import net.anasa.util.Listing;
import net.anasa.util.resolver.ResolverException;
import net.anasa.util.resolver.logic.IResolver;

public interface ITypeResolver<K> extends IResolver<SequenceToken, K>
{
	public TokenType getType();
	
	@Override
	public default boolean matches(Listing<SequenceToken> data)
	{
		if(data.size() != 1)
		{
			return false;
		}
		
		SequenceToken item = data.get(0);
		return item.getType() == getType() && matches(item);
	}
	
	public default boolean matches(SequenceToken item)
	{
		return true;
	}
	
	@Override
	public default K resolve(Listing<SequenceToken> data) throws ResolverException
	{
		return resolve(data.get(0));
	}
	
	public K resolve(SequenceToken item) throws ResolverException;
}
