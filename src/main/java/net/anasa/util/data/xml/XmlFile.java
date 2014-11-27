package net.anasa.util.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import net.anasa.util.data.DataConform.FormatException;

import org.w3c.dom.Document;

public class XmlFile
{
	private final XmlElement baseElement;
	
	public XmlFile(XmlElement baseElement) throws XmlException
	{
		this.baseElement = baseElement;
	}
	
	public XmlElement getBaseElement()
	{
		return baseElement;
	}
	
	public static XmlFile read(File file) throws XmlException
	{
		try
		{
			return read(new FileInputStream(file));
		}
		catch(FileNotFoundException e)
		{
			throw new XmlException(e);
		}
	}
	
	public static XmlFile read(InputStream stream) throws XmlException
	{
		if(stream == null)
		{
			throw new XmlException("InputStream cannot be null");
		}
		
		try
		{
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
			doc.getDocumentElement().normalize();
			
			return new XmlFile(XmlElement.get(doc.getDocumentElement()));
		}
		catch(XmlException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new XmlException(e);
		}
	}
	
	public static class XmlException extends FormatException
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
