package rchs.tsa.math.resource.module.internal.ui;

import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.module.context.IComponentEntry;
import rchs.tsa.math.resource.module.context.IComponentHandler;

public abstract class ComponentBuilder<T extends IComponent> implements IComponentEntry
{
	private final Listing<ComponentAspect<?>> aspects = new Listing<>();
	
	public Listing<ComponentAspect<?>> getAspects()
	{
		return aspects;
	}
	
	public abstract IComponentHandler buildHandler(AspectData data);
	
	@Override
	public IComponentHandler getHandler(Properties props) throws Exception
	{
		return buildHandler(new AspectData(props, this));
	}
	
	public class ComponentAspect<A>
	{
		private final String name;
		private final IFormat<A> format;
		private final A def;
		
		public ComponentAspect(String name, IFormat<A> format, A def)
		{
			this.name = name;
			this.format = format;
			this.def = def;
			
			getAspects().add(this);
		}
		
		public String getName()
		{
			return name;
		}

		public IFormat<A> getFormat()
		{
			return format;
		}
		
		public A getDefault()
		{
			return def;
		}
		
		public A getValue(AspectData data)
		{
			try
			{
				return getFormat().getFrom(data.data(this));
			}
			catch(FormatException e)
			{
				return getDefault();
			}
		}
	}
}
