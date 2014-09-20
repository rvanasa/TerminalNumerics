package net.anasa.util.data.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlReader implements IReader<InputStream>
{
	private final URL url;
	
	public UrlReader(URL url)
	{
		this.url = url;
	}
	
	public UrlReader(String url) throws MalformedURLException
	{
		this(new URL(url));
	}

	public URL getURL()
	{
		return url;
	}

	@Override
	public InputStream read() throws IOException
	{
		try
		{
			InputStream input = getURL().openStream();
			
			if(input == null)
			{
				throw new IOException("Invalid URL: " + getURL());
			}
			
			return input;
		}
		catch(Exception e)
		{
			throw new IOException("Exception occurred while parsing URL: " + getURL(), e);
		}
	}
}
