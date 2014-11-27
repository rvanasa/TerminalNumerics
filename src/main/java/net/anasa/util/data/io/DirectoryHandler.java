package net.anasa.util.data.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.anasa.util.data.format.IFormat;

public class DirectoryHandler<T> implements IDirectoryHandler<String, T>
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
	public Map<String, T> load() throws IOException
	{
		getDir().mkdirs();
		
		if(!getDir().isDirectory())
		{
			throw new IOException("Invalid directory: " + getDir());
		}
		
		Map<String, T> map = new HashMap<>();
		
		for(File file : getDir().listFiles())
		{
			if(file.isFile() && (!hasExtension() || file.getName().endsWith(getExtension())))
			{
				map.put(getKey(file), new FileHandler<>(file, getFormat()).load());
			}
		}
		
		return map;
	}
	
	@Override
	public void write(Map<String, T> data) throws IOException
	{
		getDir().mkdirs();
		
		for(String key : data.keySet())
		{
			new FileHandler<>(getFile(key), getFormat()).write(data.get(key));
		}
	}
	
	public String getKey(File file)
	{
		String name = file.getName();
		
		if(hasExtension())
		{
			name = name.substring(0, name.length() - getExtension().length());
		}
		
		return name;
	}
	
	public File getFile(String key)
	{
		return new File(getDir(), hasExtension() ? key + getExtension() : key);
	}
	
	@Override
	public IHandler<T> getHandler(String key)
	{
		return new FileHandler<>(getFile(key), getFormat());
	}
	
	@Override
	public String toString()
	{
		return getDir().getAbsolutePath();
	}
}
