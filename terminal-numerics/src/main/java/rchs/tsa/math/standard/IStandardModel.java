package rchs.tsa.math.standard;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public interface IStandardModel extends IStandardNode
{
	public String getName();
	
	public Listing<IStandardGradeLevel> getGradeLevels();
	
	default IStandardGradeLevel getGradeByID(String id)
	{
		return getGradeLevels().getFirst((grade) -> StringHelper.equalsIgnoreCase(grade.getID(), id));
	}
}
