package rchs.tsa.math.io;

import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardDomain;
import rchs.tsa.math.standard.IStandardGradeLevel;
import rchs.tsa.math.standard.IStandardModel;
import rchs.tsa.math.standard.Standard;
import rchs.tsa.math.standard.StandardDomain;
import rchs.tsa.math.standard.StandardGradeLevel;
import rchs.tsa.math.standard.StandardModel;
import net.anasa.util.Listing;
import net.anasa.util.data.properties.IPropertiesLoader;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.data.properties.PropertiesException;

public class PropsStandardModelLoader implements IPropertiesLoader<Listing<IStandardModel>>
{
	@Override
	public Listing<IStandardModel> load(Properties props) throws PropertiesException
	{
		Listing<IStandardModel> models = new Listing<>();
		for(Properties modelData : props.getInnerProps().getValues())
		{
			Listing<IStandardGradeLevel> gradeLevels = new Listing<>();
			IStandardModel model = new StandardModel(props.getString("model").toUpperCase(), props.getString("name"), gradeLevels);
			for(Properties gradeData : modelData.getInnerProps().getValues())
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
			
			models.add(new StandardModel(modelData.getKey(), modelData.getValue(), gradeLevels));
		}
		
		return models;
	}
}
