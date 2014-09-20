package net.anasa.util.data.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import net.anasa.util.Checks;

public final class IOHelper
{
	public static String read(InputStream stream) throws IOException
	{
		Checks.checkNotNull(stream, new IOException("InputStream cannot be null"));
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream)))
		{
			String data = "";
			while(reader.ready())
			{
				data += reader.readLine() + '\n';
			}
			
			return data;
		}
	}
	
	public static void write(OutputStream stream, String data) throws IOException
	{
		Checks.checkNotNull(stream, new IOException("OutputStream cannot be null"));
		Checks.checkNotNull(data, new IOException("Output data cannot be null"));
		
		try(PrintWriter writer = new PrintWriter(stream))
		{
			writer.print(data);
		}
	}
}
