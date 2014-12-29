package rchs.tsa.math.sequence;

import java.util.function.Predicate;

import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ITokenType;

public final class SequenceNesting
{
	public static boolean isNestingValid(Listing<IToken> data)
	{
		ITokenType lastType = null;
		
		int stack = 0;
		for(IToken token : data)
		{
			if(isNestingToken(token.getType()))
			{
				stack++;
				lastType = token.getType();
			}
			else if(isCorrespondingToken(token.getType()))
			{
				stack--;
				
				if(stack < 0 || lastType == null || token.getType() != getCorrespondingType(lastType))
				{
					return false;
				}
			}
		}
		
		return stack == 0;
	}
	
	public static boolean isNestingToken(ITokenType type)
	{
		return type == ExpressionTokenType.OPEN_PARENTHESIS;
	}
	
	public static boolean isCorrespondingToken(ITokenType type)
	{
		return type == ExpressionTokenType.CLOSE_PARENTHESIS;
	}
	
	public static ITokenType getCorrespondingType(ITokenType type)
	{
		if(isNestingToken(type))
		{
			return ExpressionTokenType.CLOSE_PARENTHESIS;
		}
		
		return null;
	}
	
	public static int getCorresponding(Listing<IToken> data, int index) throws FormatException
	{
		ITokenType open = data.get(index).getType();
		ITokenType close = getCorrespondingType(open);
		
		int nest = 0;
		for(int i = index; i < data.size(); i++)
		{
			IToken item = data.get(i);
			
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
		
		throw new FormatException("Could not find corresponding for item at index: " + index);
	}
	
	public static Listing<IToken> getBlock(Listing<IToken> data, int index) throws FormatException
	{
		return data.subList(index, getCorresponding(data, index) + 1);
	}
	
	public static Listing<IToken> stripNesting(Listing<IToken> data) throws FormatException
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
