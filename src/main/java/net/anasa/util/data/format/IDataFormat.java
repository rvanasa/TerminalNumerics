package net.anasa.util.data.format;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public interface IDataFormat<T> extends IConformHandler<String, T>
{
	public default String getFormatted(T data) throws FormatException
	{
		return String.valueOf(data);
	}
	
	@Override
	public abstract T getFrom(String data) throws FormatException;
	
	public default IConformHandler<T, String> reverse()
	{
		return (data) -> getFormatted(data);
	}
	
	public static final IDataFormat<String> STRING = (data) -> data;
	
	public static final IDataFormat<Integer> INT = (data) -> {
		try
		{
			return Integer.decode(data);
		}
		catch(NumberFormatException e)
		{
			throw new FormatException(e);
		}
	};
	
	public static final IDataFormat<Double> DOUBLE = (data) -> {
		try
		{
			return Double.parseDouble(data);
		}
		catch(NumberFormatException e)
		{
			throw new FormatException(e);
		}
	};
	
	public static final IDataFormat<Float> FLOAT = (data) -> {
		try
		{
			return Float.parseFloat(data);
		}
		catch(NumberFormatException e)
		{
			throw new FormatException(e);
		}
	};
	
	public static final IDataFormat<Boolean> BOOLEAN = (data) -> {
		if(data == null)
		{
			throw new FormatException("Boolean cannot be determined from a null string");
		}
		
		switch(data.toUpperCase())
		{
		case "TRUE":
			return true;
		case "FALSE":
			return false;
		default:
			throw new FormatException("Invalid boolean: " + data);
		}
	};
}
