package net.anasa.util.data.format;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.DataConform.FormatException;

public abstract class NodeDataFormat<T> implements IFormat<T>
{
	private static final String START = "[", END = "]";
	
	private final String splitter;
	
	private final Listing<FormatNode<?>> nodes = new Listing<FormatNode<?>>();
	
	public NodeDataFormat(String splitter)
	{
		this.splitter = splitter;
	}
	
	public NodeDataFormat()
	{
		this(";");
	}
	
	public String getSplitter()
	{
		return splitter;
	}

	public abstract void save(T data);
	
	public abstract T load();
	
	@Override
	public String getFormatted(T data) throws FormatException
	{
		save(data);
		
		String ret = "";
		
		for(FormatNode<?> prop : nodes)
		{
			String formatted = prop.getFormatted();
			
			if(formatted.contains(getSplitter()))
			{
				throw new FormatException("Data cannot contain format splitter");
			}
			
			ret = StringHelper.concat(getSplitter(), ret, formatted);
		}
		
		return START + ret + END;
	}
	
	@Override
	public T getFrom(String data) throws FormatException
	{
		if(data == null || !(data.startsWith(START) && data.endsWith(END)))
		{
			throw new FormatException("Invalid data format: " + data);
		}
		
		String[] items = data.substring(START.length(), data.length() - END.length()).split(getSplitter());
		
		if(items.length != nodes.size())
		{
			throw new FormatException("Invalid number of properties for data: " + data);
		}
		
		for(int i = 0; i < items.length; i++)
		{
			nodes.get(i).setValue(items[i]);
		}
		
		return load();
	}
	
	public class FormatNode<E>
	{
		private final IFormat<E> format;
		
		private E value;
		
		public FormatNode(IFormat<E> format)
		{
			nodes.add(this);
			
			this.format = format;
		}
		
		public IFormat<E> getFormat()
		{
			return format;
		}

		public E getValue()
		{
			return value;
		}
		
		public void setValue(E value)
		{
			this.value = value;
		}
		
		public void setValue(String data) throws FormatException
		{
			setValue(getFrom(data));
		}
		
		public E getFrom(String data) throws FormatException
		{
			return getFormat().getFrom(data);
		}
		
		public String getFormatted() throws FormatException
		{
			return getFormat().getFormatted(getValue());
		}
	}
}
