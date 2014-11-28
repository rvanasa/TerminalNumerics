package net.anasa.util.data.parser;

import net.anasa.util.data.DataConform.FormatException;

public interface IParserPattern<T>
{
	public boolean isValid(String data);
	
	public T compile(String data) throws FormatException;
}
