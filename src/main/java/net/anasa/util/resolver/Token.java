package net.anasa.util.resolver;

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

	@Override
	public String getData()
	{
		return data;
	}
}
