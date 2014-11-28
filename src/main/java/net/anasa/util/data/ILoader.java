package net.anasa.util.data;

import net.anasa.util.data.DataConform.FormatException;

public interface ILoader<I, O>
{
	public O load(I input) throws FormatException;
}
