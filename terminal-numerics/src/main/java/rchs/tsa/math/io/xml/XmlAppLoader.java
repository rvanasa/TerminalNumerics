package rchs.tsa.math.io.xml;

import java.awt.Image;

import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.xml.IXmlLoader;
import net.anasa.util.data.xml.XmlElement;
import rchs.tsa.math.io.xml.layout.XmlLayoutLoader;
import rchs.tsa.math.io.xml.layout.node.ILayoutNode;
import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.app.App;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.standard.IStandard;

public class XmlAppLoader implements IXmlLoader<IApp>
{
	private final ModuleContext context;
	
	private final XmlLayoutLoader layoutLoader;
	
	private final String id;
	private final Image icon;
	
	public XmlAppLoader(ModuleContext context, String id, Image icon)
	{
		this.context = context;
		
		layoutLoader = new XmlLayoutLoader(context);

		this.id = id;
		this.icon = icon;
	}
	
	public XmlLayoutLoader getLayoutLoader()
	{
		return layoutLoader;
	}
	
	@Override
	public IApp load(XmlElement element) throws FormatException
	{
		String id = element.getValue("id", this.id);
		Version version = Version.getFrom(element.getValue("version"));
		String name = element.getValue("name");
		String description = element.getValue("description", "(No description provided)");
		
		IStandard[] standards = new Listing<>(element.getValue("standards", "").split(",|;"))
				.filter((data) -> !data.trim().isEmpty())
				.conform((data) -> context.getStandard(data))
				.filter((data) -> data != null)
				.sort((a, b) -> a.getName().compareTo(b.getName()))
				.toArray(IStandard.class);
		
		ILayoutNode launchComponent = getLayoutLoader().load(element.getElement("layout"));
		
		Dependency[] dependencies = new Listing<>(element.getValue("dependencies", "").split(",|;"))
				.filter((data) -> !data.trim().isEmpty())
				.conform((data) -> Dependency.getFrom(data))
				.toArray(Dependency.class);
		
		return new App(id, version, name, description, standards, icon, (props) -> launchComponent.compile(context), dependencies);
	}
}
