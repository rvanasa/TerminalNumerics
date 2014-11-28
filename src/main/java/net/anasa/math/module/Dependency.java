package net.anasa.math.module;

import net.anasa.util.Checks;
import net.anasa.util.data.DataConform.FormatException;

public class Dependency
{
	public static Dependency getFrom(String data) throws FormatException
	{
		Checks.checkNotNull(data, new FormatException("data cannot be null"));
		
		boolean hasVersion = data.contains(":");
		
		String id = hasVersion ? data.substring(0, data.indexOf(":")) : data;
		Version version = hasVersion ? Version.getFrom(data.substring(data.indexOf(":") + 1)) : null;
		
		return new Dependency(id, version);
	}
	
	private final String id;
	
	private final Version version;
	
	public Dependency(String id, Version version)
	{
		this.id = id;
		
		this.version = version;
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
}
