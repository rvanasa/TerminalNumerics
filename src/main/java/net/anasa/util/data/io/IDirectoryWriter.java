package net.anasa.util.data.io;

import java.io.IOException;
import java.util.Map;

import net.anasa.util.Checks;

public interface IDirectoryWriter<K, V> extends IWriter<Map<K, V>>
{
	public IWriter<V> getWriter(K key);
	
	public default void write(K key, V data) throws IOException
	{
		IWriter<V> writer = getWriter(key);
		Checks.checkNotNull(writer, new IOException("Invalid directory key: " + key));
		writer.write(data);
	}
}
