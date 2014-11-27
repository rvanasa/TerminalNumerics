package net.anasa.util.data.xml;

import net.anasa.util.Checks;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.data.xml.XmlFile.XmlException;

public interface IXmlLoader<T>
{
	public T load(XmlElement element) throws FormatException;
	
	default XmlElement getElement(String key, XmlElement element, XmlElement def)
	{
		XmlElement inner = element.getElement(key);
		return inner != null ? inner : def;
	}
	
	default XmlElement getElement(String key, XmlElement element) throws XmlException
	{
		return Checks.checkNotNull(getElement(key, element, null), new XmlException("Required element does not exist: " + key + " [" + element + "]"));
	}
	
	default String getAttribute(String key, XmlElement element) throws XmlException
	{
		return Checks.checkNotNull(getAttribute(key, element, null), new XmlException("Required annotation does not exist: " + key + " [" + element + "]"));
	}
	
	default String getAttribute(String key, XmlElement element, String def)
	{
		String data = element.getAttribute(key);
		return data != null ? data : def;
	}
	
	default String getElementData(String key, XmlElement element) throws XmlException
	{
		return getElement(key, element).getData();
	}
	
	default String getElementData(String key, XmlElement element, String def)
	{
		XmlElement inner = element.getElement(key);
		return inner != null ? inner.getData() : def;
	}
	
	default String getValue(String key, XmlElement element) throws XmlException
	{
		return Checks.checkNotNull(getValue(key, element, null), new XmlException("Required value does not exist: " + key + " [" + element + "]"));
	}
	
	default String getValue(String key, XmlElement element, String def)
	{
		return getElementData(key, element, getAttribute(key, element, def));
	}
	
	default Properties getProperties(XmlElement element)
	{
		return getProperties(element, new Properties());
	}
	
	default Properties getProperties(XmlElement element, Properties props)
	{
		for(XmlAttribute attribute : element.getAttributes())
		{
			props.set(attribute.getKey(), attribute.getValue());
		}
		
		for(XmlElement child : element.getElements())
		{
			if(child.hasData())
			{
				props.set(child.getName(), child.getData());
			}
			
			getProperties(child, props.getInner(child.getName()));
		}
		
		return props;
	}
}
