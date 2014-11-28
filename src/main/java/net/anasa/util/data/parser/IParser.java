package net.anasa.util.data.parser;

import net.anasa.util.Listing;
import net.anasa.util.data.DataConform.FormatException;

public interface IParser<T>
{
	public Listing<T> parse(String data) throws FormatException;
}
