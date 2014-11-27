package net.anasa.math.standard;

public interface IStandardGrade extends IStandardNode
{
	public IStandardModel getModel();
	
	public String getName();
	
	public IStandardDomain getDomainByID(String id);
}
