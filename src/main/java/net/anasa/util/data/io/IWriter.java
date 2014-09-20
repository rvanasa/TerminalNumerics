package net.anasa.util.data.io;

import java.io.IOException;

public interface IWriter<T>
{
	public void write(T data) throws IOException;
}
