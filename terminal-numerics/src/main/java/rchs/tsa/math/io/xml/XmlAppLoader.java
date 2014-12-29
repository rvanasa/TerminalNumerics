package rchs.tsa.math.io.xml;

import java.awt.Image;

import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.app.App;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.ui.xml.ILayoutNode;
import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
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
		
		IStandard[] standards = new Listing<>(getValue("standards", element, "").split(",|;"))
				.filter((data) -> !data.trim().isEmpty())
				.conform((data) -> context.getStandard(data))
				.filter((data) -> data != null)
				.sort((a, b) -> a.getName().compareTo(b.getName()))
				.toArray(IStandard.class);
		
		ILayoutNode launchComponent = new XmlLayoutLoader().load(element.getElement("layout"));
		
		Dependency[] dependencies = new Listing<>(getValue("dependencies", element, "").split(",|;"))
				.filter((data) -> !data.trim().isEmpty())
				.conform((data) -> Dependency.getFrom(data))
				.toArray(Dependency.class);
		
		return new App(id, version, name, description, standards, icon, (props) -> launchComponent.compile(context), dependencies);
	}
}
