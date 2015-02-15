package rchs.tsa.math.resource.module.internal.ui;

import net.anasa.util.EnumHelper;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.function.ExceptedRunnable;
import net.anasa.util.type.TypeHelper;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.DoubleFieldComponent;
import net.anasa.util.ui.IntegerFieldComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.SliderComponent;
import net.anasa.util.ui.SpaceComponent;
import net.anasa.util.ui.TabbedComponent;
import net.anasa.util.ui.TabbedComponent.TabPosition;
import net.anasa.util.ui.UIComponent.CursorType;
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
			component.setTabPosition(props.getEnum("side", TabPosition.TOP));
			for(Properties inner : props.getInnerList("_"))
			{
				component.addTab(inner.getString("title", ""), getContext().createComponent(inner.getValue(), inner));
			}
			
			return component;
		});
		addComponent("space", (props) -> {
			return new SpaceComponent(props.getInt("width", 0), props.getInt("height", 0));
		});
		addComponent("label", (props) -> {
			LabelComponent label = new LabelComponent(props.getString("test", null));
			label.setBorder(4, 2);
			TypeHelper.ensure(props.getString("action", null), (action) -> {
				label.setCursor(CursorType.HAND);
				label.addClickListener((event) -> getContext().onAction(action, label));
			});
			return label;
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
		addComponent("slider", (props) -> {
			SliderComponent slider = new SliderComponent(props.getInt("min", 0), props.getInt("max", 1), props.getInt("value", 0));
			TypeHelper.ensure(props.getString("action", null), (action) -> slider.addActionListener(() -> getContext().onAction(action, slider)));
			return slider;
		});
		addComponent("integer", (props) -> {
			IntegerFieldComponent field = new IntegerFieldComponent(
					props.getInt("value", 0),
					props.getInt("min", Integer.MIN_VALUE),
					props.getInt("max", Integer.MAX_VALUE),
					props.getInt("step", 1)
					);
			TypeHelper.ensure(props.getString("action", null), (action) -> field.addActionListener(() -> getContext().onAction(action, field)));
			return field;
		});
		addComponent("decimal", (props) -> {
			DoubleFieldComponent field = new DoubleFieldComponent(
					props.getDouble("value", 0),
					props.getDouble("min", Double.MIN_VALUE),
					props.getDouble("max", Double.MAX_VALUE),
					props.getDouble("step", 1)
					);
			TypeHelper.ensure(props.getString("action", null), (action) -> field.addActionListener(() -> getContext().onAction(action, field)));
			return field;
		});
		
		addAction("close", (component, args) -> WindowComponent.getParentWindow(component).close());
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return this;
	}
}
