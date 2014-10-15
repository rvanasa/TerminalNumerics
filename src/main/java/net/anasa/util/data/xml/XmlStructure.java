package net.anasa.util.data.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlStructure
{
	private final Element baseElement;
	
	private XmlStructure(Element baseElement) throws XmlException
	{
		this.baseElement = baseElement;
	}
	
	public Element getBaseElement()
	{
		return baseElement;
	}
	
	public static XmlStructure read(InputStream stream) throws XmlException
	{
		if(stream == null)
		{
			throw new XmlException("InputStream cannot be null");
		}
		
		try
		{
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
			doc.getDocumentElement().normalize();
			
			return new XmlStructure(Element.get(doc.getDocumentElement()));
		}
		catch(Exception e)
		{
			throw new XmlException(e);
		}
	}
	
	public static class XmlException extends IOException
	{
		public XmlException(String message)
		{
			super(message);
		}
		
		public XmlException(Throwable e)
		{
			super(e);
		}
	}
	
	@Override
	public String toString()
	{
		return baseElement.toString();
	}
}
