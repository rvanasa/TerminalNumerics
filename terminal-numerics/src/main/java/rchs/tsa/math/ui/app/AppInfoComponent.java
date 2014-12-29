package rchs.tsa.math.ui.app;

import net.anasa.util.Listing;
import net.anasa.util.Lookup;
import net.anasa.util.StringHelper;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;
import rchs.tsa.math.resource.app.IApp;

public class AppInfoComponent extends PanelComponent
{
	private final IApp app;
	
	public AppInfoComponent(IApp app)
	{
		this.app = app;
		
		setBorder(4, 4);
		
		Lookup<String> data = new Lookup<String>()
				.put("ID", app.getID())
				.put("Name", app.getName())
				.put("Version", app.getVersion().toString())
				.put("Description", app.getDescription())
				.put("Common Core Standards", StringHelper.join("; ", new Listing<>(app.getStandards()).format((standard) -> standard.getName())));
		
		UIVerticalLayout layout = new UIVerticalLayout(2);
		data.forEach((key, value) -> {
			layout.add(new LabelComponent(key + ": " + value));
		});
		layout.add(new AppIconComponent(app));
		layout.apply(this);
	}
	
	public IApp getApp()
	{
		return app;
	}
}
