package net.anasa.util.data.xml;

import java.util.Arrays;

import net.anasa.util.Listing;
import net.anasa.util.data.xml.XmlStructure.XmlException;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Element
{
	public static Element get(org.w3c.dom.Element element) throws XmlException
	{
		if(element == null)
		{
			return null;
		}
		
		Listing<Element> elements = new Listing<>();
		Listing<Attribute> attributes = new Listing<>();
		
		NamedNodeMap attrs = element.getAttributes();
		
		for(int i = 0; i < attrs.getLength(); i++)
		{
			Node item = attrs.item(i);
			
			if(item instanceof Attr)
			{
				Attr attr = (Attr)item;
				
				attributes.add(Attribute.get(attr));
			}
		}
		
		if(element.hasChildNodes())
		{
			for(Node node = element.getFirstChild(); node.getNextSibling() != null; node = node.getNextSibling())
			{
				if(node instanceof org.w3c.dom.Element)
				{
					elements.add(Element.get((org.w3c.dom.Element)node));
				}
			}
		}
		
		return new Element(element.getNodeName(), elements.toArray(Element.class), attributes.toArray(Attribute.class), element.getTextContent());
	}
	
	private final String name;
	private final Element[] elements;
	private final Attribute[] attributes;
	private final String data;
	
	public Element(String name, Element[] elements, Attribute[] attributes, String data)
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
	
	public Element[] getElements()
	{
		return elements;
	}
	
	public boolean isParentElement()
	{
		return getElements().length > 0;
	}
	
	public Attribute[] getAttributes()
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
	
	public Element getElement(String name)
	{
		for(Element element : elements)
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
		for(Attribute attr : attributes)
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
