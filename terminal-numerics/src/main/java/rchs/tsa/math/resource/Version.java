package rchs.tsa.math.resource;

import net.anasa.util.Checks;
import net.anasa.util.NumberHelper;
import net.anasa.util.StringHelper;
import net.anasa.util.data.FormatException;

public class Version
{
	public static Version getFrom(String data) throws FormatException
	{
		Checks.checkNotNull(data, "data cannot be null");
		
		String[] points = data.split("\\.");
		
		int major = NumberHelper.getInteger(points[0]);
		int minor = points.length > 1 ? NumberHelper.getInteger(points[1]) : 0;
		int patch = points.length > 2 ? NumberHelper.getInteger(points[2]) : 0;
		
		return new Version(major, minor, patch);
	}
	
	private final int major;
	private final int minor;
	private final int patch;
	
	public Version(int major, int minor, int patch)
	{
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
	
	public int getMajor()
	{
		return major;
	}
	
	public int getMinor()
	{
		return minor;
	}
	
	public int getPatch()
	{
		return patch;
	}
	
	@Override
	public String toString()
	{
		return StringHelper.join(".", getMajor(), getMinor(), getPatch());
	}
	
	public boolean equals(Version other)
	{
		return getMajor() == other.getMajor() && getMinor() == other.getMinor() && getPatch() == other.getPatch();
	}
}
