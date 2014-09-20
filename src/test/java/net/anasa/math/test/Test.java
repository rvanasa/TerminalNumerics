package net.anasa.math.test;

import java.io.File;
import java.io.FileInputStream;

import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.ui.layout.xml.LayoutParser;
import net.anasa.util.ui.WindowComponent;

public class Test
{
	public static void main(String... argv) throws Exception
	{
		ModuleContext.getInstance().loadModule(new File("E:/Documents/Java/Eclipse/TestModule/test-module.jar"));
		
		new WindowComponent("Test Layout", new LayoutParser().getFrom(new FileInputStream(new File("E:/Documents/Java/Eclipse/TestModule/test-layout.xml"))).compile()).display();
	}
}
