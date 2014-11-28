package net.anasa.util.data.resolver;

public class Token implements IToken
{
	private final String type;
	private final String data;
	
	public Token(String type, String data)
	{
		this.type = type;
		this.data = data;
	}
	
	@Override
	public String getType()
	{
		return type;
	}
	
	public boolean hasData()
	{
		return getData() != null;
	}
	
	@Override
	public String getData()
	{
		return data;
	}
	
	@Override
	public String toString()
	{
		return "(" + getType() + (hasData() ? ":'" + getData() + "'" : "") + ")";
	}
}
