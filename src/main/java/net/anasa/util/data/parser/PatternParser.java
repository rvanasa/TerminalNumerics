package net.anasa.util.data.parser;

import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.data.DataConform.FormatException;

public class PatternParser<T> implements IParser<T>
{
	private final Listing<IParserPattern<T>> patterns = new Listing<>();
	
	public PatternParser()
	{
		
	}
	
	public Listing<IParserPattern<T>> getPatterns()
	{
		return patterns;
	}
	
	public PatternParser<T> add(IParserPattern<T> pattern)
	{
		getPatterns().add(pattern);
		
		return this;
	}
	
	@Override
	public Listing<T> parse(String data) throws FormatException
	{
		Listing<T> list = new Listing<>();
		parseStep(data, list);
		
		Debug.log(list);
		
		return list;
	}
	
	private void parseStep(String data, Listing<T> list) throws FormatException
	{
		if(data.isEmpty())
		{
			return;
		}
		
		for(IParserPattern<T> pattern : getPatterns())
		{
			for(int i = 1; i <= data.length(); i++)
			{
				if(i == data.length() || !pattern.isValid(data.substring(0, i + 1)))
				{
					if(pattern.isValid(data.substring(0, i)))
					{
						list.add(pattern.compile(data.substring(0, i)));
						parseStep(data.substring(i), list);
						return;
					}
					else
					{
						break;
					}
				}
			}
		}
		
		if(isCharSkippable(data.charAt(0)))
		{
			parseStep(data.substring(1), list);
		}
		else
		{
			throw new FormatException("Could not determine pattern(s): " + data);
		}
	}
	
	public boolean isCharSkippable(char c)
	{
		return Character.isWhitespace(c);
	}
}
