package net.anasa.util.data.io;

import java.io.IOException;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public class IOConformHandler<I, O> implements IHandler<O>
{
	private final IConformHandler<I, O> conformIO;
	private final IConformHandler<O, I> conformOI;
	
	private final IHandler<I> handler;
	
	public IOConformHandler(IConformHandler<I, O> conformIO, IConformHandler<O, I> conformOI, IHandler<I> handler)
	{
		this.conformIO = conformIO;
		this.conformOI = conformOI;
		
		this.handler = handler;
	}
	
	public IConformHandler<I, O> getConformIO()
	{
		return conformIO;
	}
	
	public IConformHandler<O, I> getConformOI()
	{
		return conformOI;
	}

	public IHandler<I> getHandler()
	{
		return handler;
	}
	
	@Override
	public O read() throws IOException
	{
		try
		{
			return getConformIO().getFrom(getHandler().read());
		}
		catch(FormatException e)
		{
			throw new IOException(e);
		}
	}
	
	@Override
	public void write(O data) throws IOException
	{
		try
		{
			getHandler().write(getConformOI().getFrom(data));
		}
		catch(FormatException e)
		{
			throw new IOException(e);
		}
	}
}
