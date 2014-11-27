package net.anasa.util.data.io;

import java.io.IOException;
import java.util.Map;

import net.anasa.util.Checks;

public interface IDirectoryReader<K, V> extends IReader<Map<K, V>>
{
	public IReader<V> getReader(K key);
	
	public default V read(K key) throws IOException
	{
		IReader<V> reader = getReader(key);
		Checks.checkNotNull(reader, new IOException("Invalid directory key: " + key));
		return reader.load();
	}
}
