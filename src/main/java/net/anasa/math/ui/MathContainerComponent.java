package net.anasa.math.ui;

import java.awt.Desktop;

import net.anasa.math.MathSoftware;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.ui.app.AppListComponent;
import net.anasa.math.ui.standard.StandardModelComponent;
import net.anasa.math.util.StateStandards;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.OutputComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.menu.MenuActionComponent;
import net.anasa.util.ui.menu.MenuBarComponent;
import net.anasa.util.ui.menu.MenuComponent;
import net.anasa.util.ui.menu.SeperatorComponent;

public class MathContainerComponent extends PanelComponent
{
	public MathContainerComponent(ModuleContext context)
	{
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, new MenuBarComponent(new MenuComponent[] {
				new MenuComponent("Options", new IComponent[] {
						new MenuActionComponent("View Software License", () -> new WindowComponent("Software License", new LicenseComponent()).display()),
						new MenuActionComponent("Common Core State Standards", () -> new WindowComponent("Common Core State Standards", new ScrollComponent(0, 600, new StandardModelComponent(StateStandards.getModel("CCSS")))).display()),
						new SeperatorComponent(),
						new MenuActionComponent("Exit Application", () -> WindowComponent.closeAllWindows()),
				}), new MenuComponent("Advanced", new IComponent[] {
						new MenuActionComponent("Browse Resource Directory", () -> {
							try
							{
								Desktop.getDesktop().open(MathSoftware.getDirectory());
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}),
						new MenuActionComponent("Monitor System Output", () -> new WindowComponent("System Output", new ScrollComponent(true, new OutputComponent(60, 20))).display()),
				})
		}));
		layout.set(BorderPosition.CENTER, new AppListComponent(context));
		layout.apply(this);
	}
}
