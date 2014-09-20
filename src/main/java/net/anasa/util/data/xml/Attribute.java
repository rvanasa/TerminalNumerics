package net.anasa.util.data.xml;

import org.w3c.dom.Attr;

public class Attribute
{
	public static Attribute get(Attr attr)
	{
		if(attr == null)
		{
			return null;
		}
		
		return new Attribute(attr.getName(), attr.getNodeValue());
	}
	
	private final String key, value;
	
	public Attribute(String key, String value)
	{
		this.key = key;
		this.value = value;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return getKey() + ":" + getValue();
	}
}