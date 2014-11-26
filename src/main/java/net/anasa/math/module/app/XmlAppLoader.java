package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.Version;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.standard.IStandard;
import net.anasa.math.ui.xml.LayoutParser;
import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.xml.XmlElement;
import net.anasa.util.ui.IComponent;

public final class XmlAppLoader
{
	public static IApp loadApp(ModuleContext context, XmlElement element, Image icon) throws FormatException
	{
		String id = getAnnotationSafely("id", element);
		Version version = Version.getFrom(getValueSafely("version", element));
		String name = getValueSafely("name", element);
		String description = getValueSafely("description", element);
		
		IStandard[] standards = new Listing<>(getValueSafely("standards", element).split(",")).conform((data) -> context.getStandard(data)).toArray(IStandard.class);
		
		IComponent launchComponent = new LayoutParser().getFrom(element.getElement("layout")).compile(context);
		
		return new App(id, version, name, description, standards, icon, launchComponent);
	}
	
	private static String getAnnotationSafely(String key, XmlElement element) throws FormatException
	{
		return Checks.checkNotNull(element.getAttribute(key), new FormatException("Required annotation does not exist: " + key + " [" + element + "]"));
	}
	
	private static String getValueSafely(String inner, XmlElement element) throws FormatException
	{
		XmlElement innerElement = element.getElement(inner);
		Checks.checkNotNull(innerElement, new FormatException("Required element does not exist: " + inner + " " + element));
		
		return innerElement.getData();
	}
}
