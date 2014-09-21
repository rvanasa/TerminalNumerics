package net.anasa.math.test;

import java.io.File;

import net.anasa.math.launcher.MathLauncher;

public class Test
{
	public static void main(String... argv) throws Exception
	{
		new MathLauncher(new File("data"));
	}
}
