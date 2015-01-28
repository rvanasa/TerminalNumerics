package rchs.tsa.math.resource.module.context.base;

import net.anasa.util.Checks;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.properties.Properties;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardDomain;
import rchs.tsa.math.standard.IStandardGradeLevel;
import rchs.tsa.math.standard.IStandardModel;
import rchs.tsa.math.standard.properties.PropsStandardModel;

public class StateStandards
{
	private final Properties standards = new Properties();
	
	public StateStandards()
	{
		
	}
	
	public Properties getStandards()
	{
		return standards;
	}
	
	public IStandardModel getModel(String id)
	{
		if(getStandards().hasKey(id) || getStandards().hasInner(id))
		{
			return new PropsStandardModel(getStandards().getInner(id));
		}
		else
		{
			return null;
		}
	}
	
	public void load(Properties props) throws FormatException
	{
		getStandards().setAll(props);
	}
	
	public IStandard getStandard(String data) throws FormatException
	{
		data = data.trim();
		
		Checks.check(data.contains(" "), new FormatException("Invalid standard format: " + data + " (must include standard model)"));
		
		String state = data.substring(0, data.indexOf(" "));
		String itemData = data.substring(data.indexOf(" ") + 1);
		
		IStandardModel model = Checks.checkNotNull(getModel(state), new FormatException("Standard model is not registered: " + state));
		String[] items = itemData.split("\\.", 3);
		
		Checks.check(items.length == 3, new FormatException("Invalid standard format: " + data + " (should be '[model] [grade].[domain].[standard]')"));
		
		IStandardGradeLevel grade = Checks.checkNotNull(model.getGradeByID(items[0]), new FormatException("Invalid standard grade level: " + items[0]));
		IStandardDomain domain = Checks.checkNotNull(grade.getDomainByID(items[1]), new FormatException("Invalid standard domain: " + items[1]));
		IStandard standard = Checks.checkNotNull(domain.getStandardByID(items[2]), new FormatException("Invalid standard identifier: " + items[2]));
		
		return standard;
	}
}
