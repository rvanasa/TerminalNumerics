package net.anasa.math.module.test;

import net.anasa.math.module.IModuleDelegate;
import net.anasa.math.ui.GraphComponent;
import net.anasa.math.ui.GraphInputComponent;

public class TestModule implements IModuleDelegate
{
	@Override
	public void init()
	{
		addComponent("graph", (props) -> new GraphComponent(props.getString("display", "")));
		addComponent("graph_input", (props) -> new GraphInputComponent());
	}
}
