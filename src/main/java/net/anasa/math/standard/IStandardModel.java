package net.anasa.math.standard;

public interface IStandardModel extends IStandardNode
{
	public String getType();
	
	public IStandardGrade getGradeByID(String id);
}
