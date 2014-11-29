package rchs.tsa.math.ui.standard;

import java.awt.Font;

import rchs.tsa.math.standard.IStandardDomain;
import rchs.tsa.math.standard.IStandardGradeLevel;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class StandardGradeLevelComponent extends PanelComponent
{
	private final IStandardGradeLevel grade;
	
	public StandardGradeLevelComponent(IStandardGradeLevel grade)
	{
		this.grade = grade;
		
		setBorder(grade.getName(), new Font(Font.SERIF, Font.BOLD, 12));
		
		UIVerticalLayout layout = new UIVerticalLayout();
		for(IStandardDomain domain : grade.getDomains())
		{
			layout.add(new StandardDomainComponent(domain));
		}
		layout.apply(this);
	}

	public IStandardGradeLevel getGrade()
	{
		return grade;
	}
}
