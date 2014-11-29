package net.anasa.math.module;

import java.io.IOException;
import java.net.URL;

import net.anasa.math.MathSoftware;
import net.anasa.math.io.xml.XmlLayoutLoader;
import net.anasa.math.module.app.IApp;
import net.anasa.math.module.context.ActionRegistry.IComponentAction;
import net.anasa.math.module.context.IComponentEntry;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.Checks;

public interface IModuleDelegate
{
	default void init() throws Exception
	{
		
	}
	
	default ModuleContext getContext()
	{
		return MathSoftware.getContext();
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
