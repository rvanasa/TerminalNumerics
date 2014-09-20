package net.anasa.math.sequence;

import net.anasa.math.sequence.SequenceToken.TokenType;
import net.anasa.util.Listing;
import net.anasa.util.Listing.IListCondition;
import net.anasa.util.StringHelper.NestingException;

public final class SequenceNesting
{
	public static boolean isNestingValid(Listing<SequenceToken> data)
	{
		return data.count((item) -> item.getType() == TokenType.OPEN_PARENTHESIS) == data.count((item) -> item.getType() == TokenType.CLOSE_PARENTHESIS);
	}
	
	public static boolean isNestingToken(TokenType type)
	{
		return type == TokenType.OPEN_PARENTHESIS;
	}
	
	public static TokenType getCorrespondingType(TokenType type) throws NestingException
	{
		if(isNestingToken(type))
		{
			return TokenType.CLOSE_PARENTHESIS;
		}
		
		throw new NestingException("Invalid nesting item type: " + type);
	}
	
	public static int getCorresponding(Listing<SequenceToken> data, int index) throws NestingException
	{
		TokenType open = data.get(index).getType();
		TokenType close = getCorrespondingType(open);
		
		int nest = 0;
		for(int i = index; i < data.size(); i++)
		{
			SequenceToken item = data.get(i);
			
			if(item.getType() == open)
			{
				nest++;
			}
			else if(item.getType() == close)
			{
				nest--;
			}
			
			if(nest == 0)
			{
				return i;
			}
		}
		
		throw new NestingException("Could not find corresponding for item at index: " + index);
	}
	
	public static Listing<SequenceToken> getBlock(Listing<SequenceToken> data, int index) throws NestingException
	{
		return data.subList(index, getCorresponding(data, index) + 1);
	}
	
	public static Listing<SequenceToken> stripNesting(Listing<SequenceToken> data) throws NestingException
	{
		Listing<SequenceToken> list = new Listing<>(data);
		
		IListCondition<SequenceToken> condition = (item) -> isNestingToken(item.getType());
		
		while(list.contains(condition))
		{
			list.removeAll(getBlock(list, list.indexOf(condition)).getValues());
		}
		
		return list;
	}
}
