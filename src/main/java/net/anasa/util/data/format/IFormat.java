package net.anasa.util.data.format;

import net.anasa.util.Checks;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public interface IFormat<T> extends IConformHandler<String, T>
{
	public default String getFormatted(T data) throws FormatException
	{
		return String.valueOf(data);
	}
	
	@Override
	public abstract T getFrom(String data) throws FormatException;
	
	public default IConformHandler<T, String> reversed()
	{
		return (data) -> getFormatted(data);
	}
	
	public static final IFormat<String> STRING = (data) -> data;
	
	public static final IFormat<Integer> INT = (data) -> {
		try
		{
			return Integer.decode(data);
		}
		catch(NumberFormatException e)
		{
			throw new FormatException(e);
		}
	};
	
	public static final IFormat<Double> DOUBLE = (data) -> {
		try
		{
			return Double.parseDouble(data);
		}
		catch(NumberFormatException e)
		{
			throw new FormatException(e);
		}
	};
	
	public static final IFormat<Float> FLOAT = (data) -> {
		try
		{
			return Float.parseFloat(data);
		}
		catch(NumberFormatException e)
		{
			throw new FormatException(e);
		}
	};
	
	public static final IFormat<Boolean> BOOLEAN = (data) -> {
		Checks.checkNotNull(data, "Boolean value cannot be determined from a null string");
		switch(data.toLowerCase())
		{
		case "true":
			return true;
		case "false":
			return false;
		default:
			throw new FormatException("Invalid boolean: " + data);
		}
	};
	
	public static final IFormat<byte[]> BYTE_ARRAY = (data) -> {
		return data == null ? null : data.getBytes();
	};
}
