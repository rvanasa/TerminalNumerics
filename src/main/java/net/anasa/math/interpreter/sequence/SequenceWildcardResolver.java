package net.anasa.math.interpreter.sequence;

import net.anasa.math.sequence.SequenceNesting;
import net.anasa.math.sequence.SequenceToken;
import net.anasa.util.Listing;
import net.anasa.util.resolver.ResolverException;
import net.anasa.util.resolver.logic.IResolver;

public class SequenceWildcardResolver implements IResolver<SequenceToken, Listing<SequenceToken>>
{
	@Override
	public boolean matches(Listing<SequenceToken> data)
	{
		return SequenceNesting.isNestingValid(data);
	}
	
	@Override
	public Listing<SequenceToken> resolve(Listing<SequenceToken> data) throws ResolverException
	{
		return data;
	}
}
