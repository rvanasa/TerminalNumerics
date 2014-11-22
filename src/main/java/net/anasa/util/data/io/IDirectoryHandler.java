package net.anasa.util.data.io;

import java.util.Map;

public interface IDirectoryHandler<K, V> extends IDirectoryReader<K, V>, IDirectoryWriter<K, V>, IHandler<Map<K, V>>
{
	public IHandler<V> getHandler(K key);
	
	@Override
	public default IReader<V> getReader(K key)
	{
		return getHandler(key);
	}
	
	@Override
	public default IWriter<V> getWriter(K key)
	{
		return getHandler(key);
	}
}
