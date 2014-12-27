package rchs.tsa.math.sequence;

import java.util.function.Predicate;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper.NestingException;
import net.anasa.util.data.resolver.IToken;

public final class SequenceNesting
{
	public static boolean isNestingValid(Listing<IToken> data)
	{
		TokenType lastType = null;
		
		int stack = 0;
		for(IToken token : data)
		{
			if(isNestingToken(token.getType()))
			{
				stack++;
				lastType = TokenType.getFrom(token);
			}
			else if(isCorrespondingToken(token.getType()))
			{
				stack--;
				
				if(stack < 0 || lastType == null || !getCorrespondingType(lastType).isType(token.getType()))
				{
					return false;
				}
			}
		}
		
		return stack == 0;
	}
	
	public static boolean isNestingToken(String type)
	{
		return TokenType.OPEN_PARENTHESIS.isType(type);
	}
	
	public static boolean isCorrespondingToken(String type)
	{
		return TokenType.CLOSE_PARENTHESIS.isType(type);
	}
	
	public static boolean isNestingToken(TokenType type)
	{
		return isNestingToken(type.name());
	}
	
	public static TokenType getCorrespondingType(TokenType type)
	{
		if(isNestingToken(type))
		{
			return TokenType.CLOSE_PARENTHESIS;
		}
		
		return null;
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
		
		Predicate<IToken> condition = (item) -> isNestingToken(item.getType());
		
		while(list.contains(condition))
		{
			list.removeAll(getBlock(list, list.indexOf(condition)).getValues());
		}
		
		return list;
	}
}
