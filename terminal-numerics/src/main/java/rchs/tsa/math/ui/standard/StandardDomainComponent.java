package rchs.tsa.math.ui.standard;

import java.awt.Font;

import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardDomain;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class StandardDomainComponent extends PanelComponent
{
	private final IStandardDomain domain;
	
	public StandardDomainComponent(IStandardDomain domain)
	{
		this.domain = domain;
		
		setBorder(domain.getID() + " | " + domain.getDescription(), new Font(Font.SERIF, Font.PLAIN, 11));
		
		UIVerticalLayout layout = new UIVerticalLayout(6);
		for(IStandard standard : domain.getStandards())
		{
			layout.add(new StandardComponent(standard));
		}
		layout.apply(this);
	}

	public IStandardDomain getDomain()
	{
		return domain;
	}
}
