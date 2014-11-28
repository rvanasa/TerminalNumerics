package net.anasa.math.module;

import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.Checks;
import net.anasa.util.EnumHelper;
import net.anasa.util.data.DataConform.FormatException;

public class Dependency
{
	public static Dependency getFrom(String data) throws FormatException
	{
		Checks.check(data != null && data.matches(".*\\..*(:.*)?"), new FormatException("Invalid dependency format: " + data));
		
		DependencyType type = EnumHelper.getFrom(DependencyType.class, data.substring(0, data.indexOf(".")));
		data = data.substring(data.indexOf(".") + 1);
		
		boolean hasVersion = data.contains(":");
		
		String id = hasVersion ? data.substring(0, data.indexOf(":")) : data;
		Version version = hasVersion ? Version.getFrom(data.substring(data.indexOf(":") + 1)) : null;
		
		return new Dependency(type, id, version);
	}
	
	private final DependencyType type;
	
	private final String id;
	private final Version version;
	
	public Dependency(DependencyType type, String id, Version version)
	{
		this.type = type;
		
		this.id = id;
		this.version = version;
	}
	
	public DependencyType getType()
	{
		return type;
	}
	
	public String getID()
	{
		return id;
	}
	
	public Version getVersion()
	{
		return version;
	}
	
	public boolean hasVersion()
	{
		return getVersion() != null;
	}
	
	public boolean isCompatible(Version version)
	{
		if(getVersion() == null)
		{
			return true;
		}
		else if(getVersion().getMajor() > version.getMajor())
		{
			return true;
		}
		else if(getVersion().getMajor() < version.getMajor())
		{
			return false;
		}
		else if(getVersion().getMinor() > version.getMinor())
		{
			return true;
		}
		else if(getVersion().getMinor() < version.getMinor())
		{
			return false;
		}
		else if(getVersion().getPatch() >= version.getPatch())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		return getID() + (hasVersion() ? ":" + getVersion() : "");
	}
	
	public enum DependencyType
	{
		MODULE((context, id) -> context.getModule(id)),
		APP((context, id) -> context.getApp(id));
		
		private final IDependencyTypeHandle handle;
		
		private DependencyType(IDependencyTypeHandle handle)
		{
			this.handle = handle;
		}
		
		public IDataEntry getData(ModuleContext context, String id)
		{
			return handle.getData(context, id);
		}
		
		private interface IDependencyTypeHandle
		{
			public IDataEntry getData(ModuleContext context, String id);
		}
	}
}
