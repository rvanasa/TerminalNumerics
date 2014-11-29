package rchs.tsa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public interface IStandardDomain extends IStandardNode
{
	public IStandardGradeLevel getGrade();
	
	public String getDescription();
	
	public Listing<IStandard> getStandards();
	
	default IStandard getStandardByID(String id)
	{
		return getStandards().getFirst((standard) -> StringHelper.equalsIgnoreCase(standard.getID(), id));
	}
}
