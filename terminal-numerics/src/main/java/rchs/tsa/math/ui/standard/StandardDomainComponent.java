package rchs.tsa.math.ui.standard;

import java.awt.Color;
import java.awt.Font;

import net.anasa.util.StringHelper;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardDomain;

public class StandardDomainComponent extends PanelComponent
{
	private final IStandardDomain domain;
	
	public StandardDomainComponent(IStandardDomain domain)
	{
		this(domain, null);
	}
	
	public StandardDomainComponent(IStandardDomain domain, IStandard highlight)
	{
		this.domain = domain;
		
		setBorder(domain.getID() + " | " + domain.getDescription(), new Font(Font.SERIF, Font.PLAIN, 11));
		
		UIVerticalLayout layout = new UIVerticalLayout(6);
		for(IStandard standard : domain.getStandards())
		{
			StandardComponent component = new StandardComponent(standard);
			if(highlight != null && StringHelper.equals(standard.getName(), highlight.getName()))
			{
				component.setBackground(new Color(0xDDDDEE));
			}
			
			layout.add(component);
		}
		layout.apply(this);
	}

	public IStandardDomain getDomain()
	{
		return domain;
	}
}
