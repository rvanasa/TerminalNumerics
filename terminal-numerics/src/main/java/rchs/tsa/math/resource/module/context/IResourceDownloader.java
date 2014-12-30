package rchs.tsa.math.resource.module.context;

import java.io.IOException;
import java.io.InputStream;

import net.anasa.util.Listing;
import rchs.tsa.math.resource.ResourceType;

public interface IResourceDownloader
{
	public Listing<String> getAvailableResources(ResourceType type) throws IOException;
	
	public InputStream downloadResource(String id, ResourceType type) throws IOException;
}
