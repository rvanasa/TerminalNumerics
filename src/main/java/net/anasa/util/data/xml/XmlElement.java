package net.anasa.util.data.xml;

import java.util.Arrays;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.xml.XmlFile.XmlException;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XmlElement
{
	public static XmlElement get(org.w3c.dom.Element element) throws XmlException
	{
		if(element == null)
		{
			return null;
		}
		
		Listing<XmlElement> elements = new Listing<>();
		Listing<XmlAttribute> attributes = new Listing<>();
		
		NamedNodeMap attrs = element.getAttributes();
		
		for(int i = 0; i < attrs.getLength(); i++)
		{
			Node item = attrs.item(i);
			
			if(item instanceof Attr)
			{
				Attr attr = (Attr)item;
				
				attributes.add(XmlAttribute.get(attr));
			}
		}
		
		if(element.hasChildNodes())
		{
			for(Node node = element.getFirstChild(); node.getNextSibling() != null; node = node.getNextSibling())
			{
				if(node instanceof org.w3c.dom.Element)
				{
					elements.add(XmlElement.get((org.w3c.dom.Element)node));
				}
			}
		}
		
		return new XmlElement(element.getNodeName(), elements.toArray(XmlElement.class), attributes.toArray(XmlAttribute.class), element.getTextContent());
	}
	
	private final String name;
	private final XmlElement[] elements;
	private final XmlAttribute[] attributes;
	private final String data;
	
	public XmlElement(String name, XmlElement[] elements, XmlAttribute[] attributes, String data)
	{
		this.name = name;
		this.elements = elements;
		this.attributes = attributes;
		this.data = data;
	}
	
	public String getName()
	{
		return name;
	}
	
	public XmlElement[] getElements()
	{
		return elements;
	}
	
	public XmlElement[] getElements(String name)
	{
		return new Listing<>(getElements()).filter((element) -> StringHelper.equals(element.getName(), name)).toArray(XmlElement.class);
	}
	
	public boolean isParentElement()
	{
		return getElements().length > 0;
	}
	
	public XmlAttribute[] getAttributes()
	{
		return attributes;
	}
	
	public String getData()
	{
		return data;
	}
	
	public boolean hasData()
	{
		return !(isParentElement() || getData() == null || getData().isEmpty());
	}
	
	public XmlElement getElement(String name)
	{
		for(XmlElement element : elements)
		{
			if(element.getName().equalsIgnoreCase(name))
			{
				return element;
			}
		}
		
		return null;
	}
	
	public String getAttribute(String name)
	{
		for(XmlAttribute attr : attributes)
		{
			if(attr.getKey().equalsIgnoreCase(name))
			{
				return attr.getValue();
			}
		}
		
		return null;
	}
	
	public boolean hasIDAttribute()
	{
		return getIDAttribute() != null;
	}
	
	public String getIDAttribute()
	{
		return getAttribute("id");
	}
	
	@Override
	public String toString()
	{
		return getName()
				+ (getAttributes().length > 0 ? ":" + Arrays.asList(getAttributes()) : "")
				+ ":" + (isParentElement() ? Arrays.asList(getElements()) : getData());
	}
}
