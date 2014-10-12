package net.anasa.math.standard;

public class Standard
{
	private final StandardCategory category;
	
	private final int number;
	private final String name;
	
	public Standard(StandardCategory category, int number, String name)
	{
		this.category = category;
		
		this.number = number;
		this.name = name;
	}

	public StandardCategory getCategory()
	{
		return category;
	}

	public int getNumber()
	{
		return number;
	}

	public String getName()
	{
		return name;
	}
}
