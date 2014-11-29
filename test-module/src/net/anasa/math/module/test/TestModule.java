package net.anasa.math.module.test;

import rchs.tsa.math.module.IModuleDelegate;
import rchs.tsa.math.ui.GraphComponent;
import rchs.tsa.math.ui.GraphInputComponent;

public class TestModule implements IModuleDelegate
{
	@Override
	public void init()
	{
		addComponent("graph", (props) -> new GraphComponent(props.getString("display", "")));
		addComponent("graph_input", (props) -> new GraphInputComponent());
	}
}
