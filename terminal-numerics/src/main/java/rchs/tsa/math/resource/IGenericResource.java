package rchs.tsa.math.resource;


public interface IGenericResource
{
	public String getID();
	
	public Version getVersion();
	
	public Dependency[] getDependencies();
}
