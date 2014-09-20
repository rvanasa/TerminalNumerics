package net.anasa.math.test;

import net.anasa.math.ui.GraphInputComponent;
import net.anasa.util.ui.WindowComponent;

public class Test
{
	public static void main(String... argv) throws Exception
	{
		new WindowComponent("Graph Interface", new GraphInputComponent()).display();
	}
}
