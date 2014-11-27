package net.anasa.math.standard;

public interface IStandardDomain extends IStandardNode
{
	public IStandardGrade getGrade();
	
	public String getDescription();
	
	public IStandard getStandardByID(String id);
}
