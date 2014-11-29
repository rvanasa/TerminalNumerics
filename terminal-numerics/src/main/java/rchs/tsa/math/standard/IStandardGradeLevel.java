package rchs.tsa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public interface IStandardGradeLevel extends IStandardNode
{
	public IStandardModel getModel();
	
	public String getName();

	public Listing<IStandardDomain> getDomains();
	
	default IStandardDomain getDomainByID(String id)
	{
		return getDomains().getFirst((domain) -> StringHelper.equalsIgnoreCase(domain.getID(), id));
	}
}
