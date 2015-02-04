package rchs.tsa.math.ui;

import java.awt.Desktop;

import net.anasa.util.function.ExceptedRunnable;
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
import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.ui.app.AppListComponent;
import rchs.tsa.math.ui.standard.StandardModelComponent;

public class MathContainerComponent extends PanelComponent
{
	public MathContainerComponent(ModuleContext context)
	{
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, new MenuBarComponent(new MenuComponent[] {
				new MenuComponent("Options", new IComponent[] {
						new MenuActionComponent("View Software License", () -> new WindowComponent("Software License", new LicenseComponent()).display()),
						new MenuActionComponent("Common Core State Standards", () -> new WindowComponent("Common Core State Standards", new ScrollComponent(680, 600, new StandardModelComponent(context.getStandards("CCSS")))).display()),
						new SeperatorComponent(),
						new MenuActionComponent("Exit Application", () -> WindowComponent.closeAllWindows()),
				}), new MenuComponent("Advanced", new IComponent[] {
						new MenuActionComponent("Browse Data Files", () -> new ExceptedRunnable(
								() -> Desktop.getDesktop().open(TerminalNumerics.getDirectory()),
								(e) -> e.printStackTrace())),
						new MenuActionComponent("Monitor System Output", () -> new WindowComponent("System Output", new ScrollComponent(true, new OutputComponent(60, 20))).display()),
				})
		}));
		layout.set(BorderPosition.CENTER, new AppListComponent(context));
		layout.apply(this);
	}
}
