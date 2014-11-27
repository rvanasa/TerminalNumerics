package net.anasa.math.standard;

public interface IStandardModel extends IStandardNode
{
	public String getName();
	
	public Iterable<IStandardGradeLevel> getGradeLevels();
	
	public IStandardGradeLevel getGradeByID(String id);
}
