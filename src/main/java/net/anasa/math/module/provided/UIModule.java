package net.anasa.math.module.provided;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;

import net.anasa.math.module.AbstractModule;
import net.anasa.math.module.IModuleDelegate;
import net.anasa.math.module.Version;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ProgressBarComponent;
import net.anasa.util.ui.UIComponent;

public class UIModule extends AbstractModule implements IModuleDelegate
{
	public UIModule()
	{
		super("ui_module", new Version(1, 0, 0), "UI Module", "Provides basic UI components");
	}
	
	@Override
	public void init()
	{
		getContext().getComponents().register("panel", (props) -> setup(props, new PanelComponent()));
		getContext().getComponents().register("label", (props) -> setup(props, new LabelComponent(props.getString("message"), new ImageIcon(new URL(props.getString("image"))))));
		getContext().getComponents().register("progress", (props) -> setup(props, new ProgressBarComponent(props.getString("key"))));
		getContext().getComponents().register("button", (props) -> setup(props, new ButtonComponent(props.getString("text"), getContext().getActions().getByID(props.getString("action")))));
	}
	
	private UIComponent<?> setup(Properties props, UIComponent<?> component) throws FormatException
	{
		component.setBackground(new Color(props.getInt("background")));
		component.setForeground(new Color(props.getInt("foreground")));
		
		component.setSize(new Vector2(props.getDouble("width"), props.getDouble("height")));
		
		return component;
	}
}
