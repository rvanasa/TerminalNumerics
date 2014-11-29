package rchs.tsa.math.module;

public interface IResource
{
	public ResourceType getResourceType();
	
	public String getID();
	
	public Version getVersion();
	
	public Dependency[] getDependencies();
}
