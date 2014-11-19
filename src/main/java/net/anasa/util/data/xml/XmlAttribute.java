package net.anasa.util.data.xml;

import org.w3c.dom.Attr;

public class XmlAttribute
{
	public static XmlAttribute get(Attr attr)
	{
		if(attr == null)
		{
			return null;
		}
		
		return new XmlAttribute(attr.getName(), attr.getNodeValue());
	}
	
	private final String key, value;
	
	public XmlAttribute(String key, String value)
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