package net.anasa.math.util;

import net.anasa.math.standard.IStandard;
import net.anasa.math.standard.IStandardDomain;
import net.anasa.math.standard.IStandardGradeLevel;
import net.anasa.math.standard.IStandardModel;
import net.anasa.math.standard.Standard;
import net.anasa.math.standard.StandardDomain;
import net.anasa.math.standard.StandardGradeLevel;
import net.anasa.math.standard.StandardModel;
import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;

public final class StateStandards
{
	private static final Listing<IStandardModel> STANDARDS = new Listing<>();
	
	public static IStandardModel getModel(String id)
	{
		return STANDARDS.getFirst((model) -> StringHelper.equalsIgnoreCase(model.getID(), id));
	}
	
	public static IStandardModel loadModel(Properties props) throws FormatException
	{
		IStandardModel model = createModel(props);
		STANDARDS.add(model);
		
		return model;
	}
	
	public static IStandardModel createModel(Properties props) throws FormatException
	{
		Listing<IStandardGradeLevel> gradeLevels = new Listing<>();
		IStandardModel model = new StandardModel(props.getString("model").toUpperCase(), props.getString("name"), gradeLevels);
		for(Properties gradeData : props.getInner("standards").getInnerProps().getValues())
		{
			Listing<IStandardDomain> domains = new Listing<>();
			IStandardGradeLevel gradeLevel = new StandardGradeLevel(model, gradeData.getKey().toUpperCase(), gradeData.getValue(), domains);
			for(Properties domainData : gradeData.getInnerProps().getValues())
			{
				Listing<IStandard> standards = new Listing<>();
				IStandardDomain domain = new StandardDomain(gradeLevel, domainData.getKey().toUpperCase(), domainData.getValue(), standards);
				for(String standardID : domainData.getKeys())
				{
					standards.add(new Standard(domain, standardID.toUpperCase(), domainData.getString(standardID)));
				}
				
				domains.add(domain);
			}
			
			gradeLevels.add(gradeLevel);
		}
		
		return model;
	}
	
	public static IStandard getStandard(String data) throws FormatException
	{
		data = data.trim();
		
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
