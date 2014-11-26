package net.anasa.math.test;

import java.io.File;

import net.anasa.math.launcher.MathLauncher;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.ui.app.AppListComponent;

public class Test
{
	public static void main(String... argv) throws Exception
	{
		new MathLauncher(new File("data"), () -> new AppListComponent(ModuleContext.getInstance().getApps().getApps()));
	}
}
