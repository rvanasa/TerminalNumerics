package rchs.tsa.math.resource.module.internal.ui;

import net.anasa.util.EnumHelper;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.function.ExceptedRunnable;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.TabbedComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.layout.ILayout;
import rchs.tsa.math.io.xml.layout.node.LayoutType;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.module.AbstractModule;
import rchs.tsa.math.resource.module.IModuleDelegate;
import rchs.tsa.math.ui.app.AppPanelComponent;

public class UIModule extends AbstractModule implements IModuleDelegate
{
	public UIModule()
	{
		super("ui_module", new Version(1, 0, 0), "UI Module", "Provides basic UI components");
	}
	
	@Override
	public void init()
	{
		addComponent("app", (props) -> new AppPanelComponent(getContext().getApp(props.getString("id")), props));
		addComponent("layout", (props) -> {
			LayoutType type = EnumHelper.getFrom(LayoutType.class, props.getString("type", LayoutType.BORDER.name()));
			
			ILayout layout = type.getLayout(props);
			
			for(Properties child : props.getInnerList("_"))
			{
				layout.set(child.getString("pos", null), getContext().createComponent(child.getValue(), child));
			}
			
			return new PanelComponent(layout);
		});
		addComponent("tabs", (props) -> {
			TabbedComponent component = new TabbedComponent();
			for(Properties inner : props.getInnerList("_"))
			{
				component.addTab(inner.getString("title", ""), getContext().createComponent(inner.getValue(), inner));
			}
			
			return component;
		});
		addComponent("panel", new UIComponentBuilder()
		{
			@Override
			public PanelComponent getComponent(AspectData data)
			{
				PanelComponent panel = new PanelComponent();
				return panel;
			}
		});
		addComponent("label", new UIComponentBuilder()
		{
			ComponentAspect<String> text = new ComponentAspect<String>("text", IFormat.STRING, null);
			
			@Override
			public LabelComponent getComponent(AspectData data)
			{
				LabelComponent label = new LabelComponent(data.get(text));
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
				ButtonComponent button = new ButtonComponent(data.get(text));
				button.addActionListener(new ExceptedRunnable(
						() -> getContext().onAction(data.get(action), button),
						(e) -> e.printStackTrace()));
				return button;
			}
		});
		
		addAction("close", (component, args) -> WindowComponent.getParentWindow(component).close());
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return this;
	}
}
