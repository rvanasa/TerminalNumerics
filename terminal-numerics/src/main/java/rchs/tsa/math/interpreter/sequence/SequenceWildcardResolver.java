package rchs.tsa.math.interpreter.sequence;

import rchs.tsa.math.sequence.SequenceNesting;
import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;
import net.anasa.util.data.resolver.logic.IResolver;

public class SequenceWildcardResolver implements IResolver<Listing<IToken>>
{
	@Override
	public boolean matches(Listing<IToken> data)
	{
		return SequenceNesting.isNestingValid(data);
	}
	
	@Override
	public Listing<IToken> resolve(Listing<IToken> data) throws ResolverException
	{
		return data;
	}
}
