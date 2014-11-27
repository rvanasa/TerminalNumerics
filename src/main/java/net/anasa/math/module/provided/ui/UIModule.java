package net.anasa.math.module.provided.ui;

import net.anasa.math.module.Module;
import net.anasa.math.module.Version;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.WindowComponent;

public class UIModule extends Module
{
	public UIModule()
	{
		super("ui_module", new Version(1, 0, 0), "UI Module", "Provides basic UI components");
	}
	
	@Override
	public void init()
	{
		addComponent("panel", new CommonComponentBuilder()
		{
			@Override
			public PanelComponent getComponent(AspectData data)
			{
				return new PanelComponent();
			}
		});
		addComponent("label", new CommonComponentBuilder()
		{
			ComponentAspect<String> text = new ComponentAspect<String>("text", IFormat.STRING, null);
			
			@Override
			public LabelComponent getComponent(AspectData data)
			{
				return new LabelComponent(data.getValue(text));
			}
		});
		addComponent("button", new CommonComponentBuilder()
		{
			ComponentAspect<String> text = new ComponentAspect<String>("text", IFormat.STRING, "");
			ComponentAspect<String> action = new ComponentAspect<String>("action", IFormat.STRING, null);
			
			@Override
			public ButtonComponent getComponent(AspectData data)
			{
				ButtonComponent button = new ButtonComponent(data.getValue(text));
				button.addActionListener(() -> getContext().getActions().onAction(data.getValue(action), button));
				
				return button;
			}
		});
		
		addAction("close", (component) -> WindowComponent.getParentWindow(component).close());
	}
}
