package net.anasa.util.data;

public final class ParamHelper
{
	public static String format(String data, Param... params)
	{
		for(Param param : params)
		{
			data = data.replace('{' + param.getKey() + '}', param.getValue());
		}
		
		return data;
	}
	
	public static class Param
	{
		private final String key, value;
		
		public Param(String key, String value)
		{
			this.key = key;
			this.value = value;
		}

		public String getKey()
		{
			return key;
		}

		public String getValue()
		{
			return value;
		}
	}
}
