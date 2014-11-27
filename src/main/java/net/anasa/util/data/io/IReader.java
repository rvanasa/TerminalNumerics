package net.anasa.util.data.io;

import java.io.IOException;

public interface IReader<T>
{
	public T load() throws IOException;
}
