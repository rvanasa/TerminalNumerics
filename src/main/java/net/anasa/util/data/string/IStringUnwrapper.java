package net.anasa.util.data.string;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;

public interface IStringUnwrapper
{
	public Properties unwrap(String data) throws FormatException;
	
	public String wrap(Properties props) throws FormatException;
}
