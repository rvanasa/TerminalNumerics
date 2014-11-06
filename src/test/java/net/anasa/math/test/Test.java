package net.anasa.math.test;

import java.io.File;

import javax.swing.ImageIcon;

import net.anasa.math.launcher.MathLauncher;
import net.anasa.math.module.Version;
import net.anasa.math.module.app.App;
import net.anasa.math.module.app.IApp;
import net.anasa.math.standard.IStandard;
import net.anasa.math.ui.app.AppListComponent;
import net.anasa.util.Listing;
import net.anasa.util.data.properties.KVPair;
import net.anasa.util.data.properties.Properties;

public class Test
{
	public static void main(String... argv) throws Exception
	{
		new MathLauncher(new File("data"), new AppListComponent(new Listing<>(new IApp[] {
				new App("test1", new Version(0, 0, 1), "Test App #1", "Testing app fo show", new IStandard[] {}, new ImageIcon(Test.class.getResource("/ui/test_icon.png")).getImage(), "graph", new Properties(new KVPair("display", "2x^3"))),
				new App("test2", new Version(1, 2, 3), "Test App Number 2", "Another testing app fo show", new IStandard[] {}, new ImageIcon().getImage(), "panel", null),
		})));
	}
}
