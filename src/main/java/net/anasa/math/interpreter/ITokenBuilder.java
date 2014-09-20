package net.anasa.math.interpreter;

import net.anasa.math.MathException;
import net.anasa.math.sequence.SequenceToken;

public interface ITokenBuilder
{
	public boolean isValid(String data);
	
	public SequenceToken getToken(String data) throws MathException;
}
