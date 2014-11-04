package net.anasa.math.sequence;

import net.anasa.math.sequence.SequenceToken.TokenType;
import net.anasa.util.Listing;
import net.anasa.util.Listing.IListCondition;
import net.anasa.util.StringHelper.NestingException;
import net.anasa.util.resolver.IToken;

public final class SequenceNesting
{
	public static boolean isNestingValid(Listing<IToken> data)
	{
		return data.count((item) -> TokenType.OPEN_PARENTHESIS.isType(item.getType())) == data.count((item) -> TokenType.CLOSE_PARENTHESIS.isType(item.getType()));
	}
	
	public static boolean isNestingToken(String type)
	{
		return TokenType.OPEN_PARENTHESIS.isType(type);
	}
	
	public static boolean isNestingToken(TokenType type)
	{
		return isNestingToken(type.name());
	}
	
	public static TokenType getCorrespondingType(TokenType type) throws NestingException
	{
		if(isNestingToken(type))
		{
			return TokenType.CLOSE_PARENTHESIS;
		}
		
		throw new NestingException("Invalid nesting item type: " + type);
	}
	
	public static int getCorresponding(Listing<IToken> data, int index) throws NestingException
	{
		TokenType open = TokenType.getFrom(data.get(index).getType());
		TokenType close = getCorrespondingType(open);
		
		int nest = 0;
		for(int i = index; i < data.size(); i++)
		{
			IToken item = data.get(i);
			
			if(open.isType(item.getType()))
			{
				nest++;
			}
			else if(close.isType(item.getType()))
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
	
	public static Listing<IToken> getBlock(Listing<IToken> data, int index) throws NestingException
	{
		return data.subList(index, getCorresponding(data, index) + 1);
	}
	
	public static Listing<IToken> stripNesting(Listing<IToken> data) throws NestingException
	{
		Listing<IToken> list = new Listing<>(data);
		
		IListCondition<IToken> condition = (item) -> isNestingToken(item.getType());
		
		while(list.contains(condition))
		{
			list.removeAll(getBlock(list, list.indexOf(condition)).getValues());
		}
		
		return list;
	}
}
