package rchs.tsa.math.resource.module.internal.ui;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.WindowComponent;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.module.AbstractModule;
import rchs.tsa.math.resource.module.IModuleDelegate;
import rchs.tsa.math.ui.GraphComponent;
import rchs.tsa.math.ui.GraphInputComponent;

public class UIModule extends AbstractModule implements IModuleDelegate
{
	public UIModule()
	{
		super("ui_module", new Version(1, 0, 0), "UI Module", "Provides basic UI components");
	}
	
	@Override
	public void init()
	{
		addComponent("panel", new UIComponentBuilder()
		{
			@Override
			public PanelComponent getComponent(AspectData data)
			{
				return new PanelComponent();
			}
		});
		addComponent("label", new UIComponentBuilder()
		{
			ComponentAspect<String> text = new ComponentAspect<String>("text", IFormat.STRING, null);
			
			@Override
			public LabelComponent getComponent(AspectData data)
			{
				LabelComponent label = new LabelComponent(data.getValue(text));
				label.setBorder(4, 2);
				return label;
			}
		});
		addComponent("button", new UIComponentBuilder()
		{
			ComponentAspect<String> text = new ComponentAspect<String>("text", IFormat.STRING, "");
			ComponentAspect<String> action = new ComponentAspect<String>("action", IFormat.STRING, null);
			
			@Override
			public ButtonComponent getComponent(AspectData data)
			{
				ButtonComponent button = new ButtonComponent(data.getValue(text));
				button.addActionListener(() -> getContext().onAction(data.getValue(action), button));
				
				return button;
			}
		});
		
		addComponent("graph", (props) -> new GraphComponent(props.getString("display", "")));
		addComponent("graph_input", (props) -> new GraphInputComponent());
		
		addAction("close", (component) -> WindowComponent.getParentWindow(component).close());
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return this;
	}
}
