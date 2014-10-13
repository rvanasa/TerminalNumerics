package net.anasa.math.standard;

public interface IStandardCategory
{
	public String getName();
	
	public IStandard getStandard(int number);
	
	public IStandard getStandard(String name);
}
