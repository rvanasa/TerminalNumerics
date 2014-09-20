package net.anasa.util;

@Deprecated
public class StringFormer
{
	private String data = "";
	private int indent;
	private String prefix = "";
	
	public StringFormer()
	{
		
	}
	
	public StringFormer(String title)
	{
		addLine(title);
		indent(1);
	}
	
	public String getData()
	{
		return data;
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public int getIndent()
	{
		return indent;
	}
	
	public void setIndent(int indent)
	{
		this.indent = Math.max(indent, 0);
	}
	
	public void indent(int amount)
	{
		setIndent(getIndent() + amount);
	}
	
	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public void addLine()
	{
		addLine("");
	}
	
	public void addLine(String line)
	{
		for(int i = 0; i < getIndent(); i++)
		{
			line += "  ";
		}
		
		data += getPrefix() + line + '\n';
	}
}