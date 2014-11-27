package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.Version;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.standard.IStandard;
import net.anasa.math.ui.xml.ILayoutNode;
import net.anasa.math.ui.xml.XmlLayoutLoader;
import net.anasa.util.Listing;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.xml.IXmlLoader;
import net.anasa.util.data.xml.XmlElement;

public class XmlAppLoader implements IXmlLoader<IApp>
{
	private final ModuleContext context;
	
	private final Image icon;
	
	public XmlAppLoader(ModuleContext context, Image icon)
	{
		this.context = context;
		
		this.icon = icon;
	}
	
	@Override
	public IApp load(XmlElement element) throws FormatException
	{
		String id = getValue("id", element);
		Version version = Version.getFrom(getValue("version", element));
		String name = getValue("name", element);
		String description = getValue("description", element, "(No description provided)");
		
		IStandard[] standards = new Listing<>(getValue("standards", element, "").split(","))
				.filter((data) -> !data.trim().isEmpty())
				.conform((data) -> context.getStandard(data))
				.filter((data) -> data != null)
				.toArray(IStandard.class);
		
		ILayoutNode launchComponent = new XmlLayoutLoader().load(element.getElement("layout"));
		
		return new App(id, version, name, description, standards, icon, (props) -> launchComponent.compile(context));
	}
}
