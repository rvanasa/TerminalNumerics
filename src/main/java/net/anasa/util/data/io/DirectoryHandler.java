package net.anasa.util.data.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import net.anasa.util.data.format.IFormat;

public class DirectoryHandler<T> implements IHandler<HashMap<String, T>>
{
	private final File dir;
	private final IFormat<T> format;
	
	private final String extension;
	
	public DirectoryHandler(File dir, IFormat<T> format)
	{
		this(dir, format, null);
	}
	
	public DirectoryHandler(File dir, IFormat<T> format, String extension)
	{
		this.dir = dir;
		this.format = format;
		
		this.extension = extension.startsWith(".") ? extension : "." + extension;
	}
	
	public File getDir()
	{
		return dir;
	}

	public IFormat<T> getFormat()
	{
		return format;
	}

	public String getExtension()
	{
		return extension;
	}
	
	public boolean hasExtension()
	{
		return getExtension() != null;
	}

	@Override
	public HashMap<String, T> read() throws IOException
	{
		getDir().mkdirs();
		
		if(!getDir().isDirectory())
		{
			throw new IOException("Invalid directory: " + getDir());
		}
		
		HashMap<String, T> map = new HashMap<String, T>();
		
		for(File file : getDir().listFiles())
		{
			if(file.isFile() && file.getName().endsWith(getExtension()))
			{
				String name = file.getName();
				
				if(hasExtension())
				{
					name = name.substring(0, name.length() - getExtension().length());
				}
				
				map.put(name, new FileHandler<>(file, getFormat()).read());
			}
		}
		
		return map;
	}
	
	@Override
	public void write(HashMap<String, T> data) throws IOException
	{
		getDir().mkdirs();
		
		for(String key : data.keySet())
		{
			File file = new File(getDir(), hasExtension() ? key + getExtension() : key);
			file.createNewFile();
			
			new FileHandler<>(file, getFormat()).write(data.get(key));
		}
	}
}
