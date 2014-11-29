package rchs.tsa.math.module;

import java.io.IOException;
import java.net.URL;

import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.io.xml.XmlLayoutLoader;
import rchs.tsa.math.module.app.IApp;
import rchs.tsa.math.module.context.IComponentEntry;
import rchs.tsa.math.module.context.ModuleContext;
import rchs.tsa.math.module.context.ActionRegistry.IComponentAction;
import net.anasa.util.Checks;

public interface IModuleDelegate
{
	default void init() throws Exception
	{
		
	}
	
	default ModuleContext getContext()
	{
		return TerminalNumerics.getContext();
	}
	
	default void addComponent(String id, IComponentEntry entry)
	{
		getContext().getComponents().register(id, entry);
	}
	
	default void addComponent(String id, URL layoutURL) throws IOException
	{
		Checks.checkNotNull(layoutURL, new IOException("layout file URL must not be null"));
		
		getContext().getComponents().register(id, (props) -> new XmlLayoutLoader().load(layoutURL.openStream()).compile(getContext()));
	}
	
	default void addAction(String id, IComponentAction action)
	{
		getContext().getActions().register(id, action);
	}
	
	default void addModule(IModule module)
	{
		getContext().addModule(module);
	}
	
	default void addApp(IApp app)
	{
		getContext().addApp(app);
	}
}
