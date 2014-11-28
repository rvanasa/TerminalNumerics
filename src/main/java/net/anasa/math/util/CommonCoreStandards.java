package net.anasa.math.util;

import net.anasa.math.standard.IStandard;
import net.anasa.math.standard.IStandardDomain;
import net.anasa.math.standard.IStandardGradeLevel;
import net.anasa.math.standard.IStandardModel;
import net.anasa.math.standard.properties.PropsStandardModel;
import net.anasa.util.Checks;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;

public final class CommonCoreStandards
{
	private static final Properties STANDARDS = new Properties();
	
	public static IStandardModel getModel(String id)
	{
		if(STANDARDS.hasKey(id) || STANDARDS.hasInner(id))
		{
			return new PropsStandardModel(STANDARDS.getInner(id));
		}
		else
		{
			return null;
		}
	}
	
	public static void load(Properties props) throws FormatException
	{
		STANDARDS.setAll(props);
	}
	
	public static IStandard getStandard(String data) throws FormatException
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
