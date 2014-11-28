package net.anasa.util.data.string;

import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;

public class SplitterUnwrapper implements IStringUnwrapper
{
	private final String splitter;
	
	private final String[] values;
	
	public SplitterUnwrapper(String splitter, String... values)
	{
		Checks.checkNotNull(splitter, "Splitter cannot be null");
		
		this.splitter = splitter;
		
		this.values = values;
	}
	
	public String getSplitter()
	{
		return splitter;
	}
	
	public String[] getValues()
	{
		return values;
	}
	
	@Override
	public Properties unwrap(String data) throws FormatException
	{
		Properties props = new Properties();
		
		data += getSplitter();
		
		for(String value : getValues())
		{
			Checks.check(data.contains(getSplitter()), new FormatException("Input does not contain required splitter(s): '" + getSplitter() + "' (" + data + ""));

			props.set(value, data.indexOf(0, data.indexOf(getSplitter())));
			
			data = data.substring(data.indexOf(getSplitter()) + getSplitter().length());
		}
		
		return props;
	}
	
	@Override
	public String wrap(Properties props) throws FormatException
	{
		return props.getString(StringHelper.join(getSplitter(), new Listing<>(getValues()).compute((key) -> props.getString(key, null))));
	}
}
