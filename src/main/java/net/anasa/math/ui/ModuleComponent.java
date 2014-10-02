package net.anasa.math.ui;

import javax.swing.BorderFactory;

import net.anasa.math.module.IUserModule;
import net.anasa.math.module.ModuleException;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class ModuleComponent extends PanelComponent
{
	private final IUserModule module;
	
	public ModuleComponent(IUserModule module) throws ModuleException
	{
		this.module = module;
		
		ModuleIconComponent icon = new ModuleIconComponent(module);
		LabelComponent label = new LabelComponent(module.getName());
		
		UIBorderLayout layout = new UIBorderLayout(4);
		layout.set(BorderPosition.LEFT, icon);
		layout.set(BorderPosition.CENTER, label);
		layout.apply(this);
		
		label.setCursor(CursorType.HAND);
		label.addClickListener((event) -> icon.getEvents().dispatch(event));
		
		getHandle().setBorder(BorderFactory.createEtchedBorder());
	}
	
	public IUserModule getModule()
	{
		return module;
	}
}
