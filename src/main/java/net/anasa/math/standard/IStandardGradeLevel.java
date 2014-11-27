package net.anasa.math.standard;

public interface IStandardGradeLevel extends IStandardNode
{
	public IStandardModel getModel();
	
	public String getName();

	public Iterable<IStandardDomain> getDomains();
	
	public IStandardDomain getDomainByID(String id);
}
