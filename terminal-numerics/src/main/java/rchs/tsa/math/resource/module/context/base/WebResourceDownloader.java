package rchs.tsa.math.resource.module.context.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.anasa.util.Listing;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.io.UrlReader;
import rchs.tsa.math.resource.ResourceType;
import rchs.tsa.math.resource.module.context.IResourceDownloader;

public class WebResourceDownloader implements IResourceDownloader
{
	private final String path;
	
	public WebResourceDownloader(String path)
	{
		this.path = path;
	}

	public String getPath()
	{
		return path;
	}

	@Override
	public Listing<String> getAvailableResources(ResourceType type) throws IOException
	{
		URL url = new URL(getPath() + "?type=" + type.getPath());
		
		return new Listing<>(new UrlReader<>(url, IFormat.STRING).read().split("\n")).filter((id) -> !id.isEmpty());
	}

	@Override
	public InputStream downloadResource(String id, ResourceType type) throws IOException
	{
		URL url = new URL(getPath() + "?type=" + type.getPath() + "&id=" + id);
		
		return url.openStream();
	}
}
