package net.anasa.math.ui;

import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.ui.app.AppListComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.menu.MenuBarComponent;
import net.anasa.util.ui.menu.MenuComponent;
import net.anasa.util.ui.menu.MenuItemComponent;

public class MathContainerComponent extends PanelComponent
{
	public MathContainerComponent(ModuleContext context)
	{
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, new MenuBarComponent(new MenuComponent[] {
				new MenuComponent("Options", new MenuItemComponent[] {
						new MenuItemComponent("View License", () -> new WindowComponent("Software License", new LicenseComponent()).display())
						})
		}));
		layout.set(BorderPosition.CENTER, new AppListComponent(context));
		layout.apply(this);
	}
}
