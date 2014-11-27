package net.anasa.math.standard;

public interface IStandardDomain extends IStandardNode
{
	public IStandardGradeLevel getGrade();
	
	public String getDescription();
	
	public Iterable<IStandard> getStandards();
	
	public IStandard getStandardByID(String id);
}
