package net.anasa.math.standard;

import net.anasa.util.StringHelper;

public interface IStandard extends IStandardNode
{
	public IStandardDomain getDomain();
	
	public String getDescription();
	
	default String getName()
	{
		IStandardGrade grade = getDomain().getGrade();
		IStandardModel model = grade.getModel();
		
		return model.getID() + " " + StringHelper.join(".", grade.getID(), getDomain().getID(), getID());
	}
}
