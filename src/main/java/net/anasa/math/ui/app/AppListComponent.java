package net.anasa.math.ui.app;

import net.anasa.math.module.app.IApp;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.util.UI;
import net.anasa.util.Listing;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.TextFieldComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class AppListComponent extends PanelComponent
{
	private final Listing<IApp> apps;
	
	private final PanelComponent appPanel;
	
	private final TextFieldComponent filterBar;
	
	private IAppFilter appFilter;
	
	public AppListComponent()
	{
		this(ModuleContext.getInstance());
	}
	
	public AppListComponent(ModuleContext context)
	{
		this(context.getApps().getApps());
	}
	
	public AppListComponent(Listing<IApp> apps)
	{
		this.apps = apps;
		
		setSize(400, 400);
		
		appPanel = new PanelComponent();
		
		filterBar = new TextFieldComponent();
		filterBar.addKeyListener((event) -> updateApps());
		
		setAppFilter((app, text) -> app.getName().contains(text) || app.getID().contains(text) || app.getDescription().startsWith(text));
		
		updateApps();
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, filterBar);
		layout.set(BorderPosition.CENTER, new ScrollComponent(appPanel));
		layout.apply(this);
	}
	
	public Listing<IApp> getApps()
	{
		return apps;
	}

	public PanelComponent getAppPanel()
	{
		return appPanel;
	}
	
	public TextFieldComponent getFilterBar()
	{
		return filterBar;
	}
	
	public IAppFilter getAppFilter()
	{
		return appFilter;
	}
	
	public void setAppFilter(IAppFilter appFilter)
	{
		this.appFilter = appFilter;
	}
	
	public void updateApps()
	{
		getAppPanel().removeComponents();
		
		UIVerticalLayout layout = new UIVerticalLayout(4);
		for(IApp app : getApps())
		{
			if(app != null && getAppFilter() != null && getAppFilter().isValid(app, getFilterBar().getValue()))
			{
				try
				{
					layout.add(new AppListEntryComponent(app));
				}
				catch(Exception e)
				{
					UI.sendError("Failed to load app: " + app.getName(), e);
				}
			}
		}
		layout.apply(getAppPanel());
	}
	
	public interface IAppFilter
	{
		public boolean isValid(IApp app, String text);
	}
}
