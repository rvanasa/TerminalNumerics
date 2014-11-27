package net.anasa.math.ui.standard;

import java.awt.Font;

import net.anasa.math.standard.IStandardGradeLevel;
import net.anasa.math.standard.IStandardModel;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class StandardModelComponent extends PanelComponent
{
	private final IStandardModel model;
	
	public StandardModelComponent(IStandardModel model)
	{
		this.model = model;
		
		setBorder(model.getID(), new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		
		UIVerticalLayout layout = new UIVerticalLayout();
		for(IStandardGradeLevel grade : model.getGradeLevels())
		{
			layout.add(new StandardGradeLevelComponent(grade));
		}
		layout.apply(this);
	}

	public IStandardModel getModel()
	{
		return model;
	}
}
