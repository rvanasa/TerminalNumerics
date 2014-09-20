package net.anasa.math.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

import net.anasa.math.module.IModule;
import net.anasa.math.module.ModuleException;
import net.anasa.util.Checks;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.event.ClickEvent.ButtonType;

public class ModuleIconComponent extends LabelComponent
{
	private static final int IMAGE_SIZE = 32;
	
	private final IModule module;
	
	public IModule getModule()
	{
		return module;
	}

	public ModuleIconComponent(IModule module) throws ModuleException
	{
		super(new ImageIcon(module.getIcon().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH)));
		
		this.module = module;
		
		setCursor(CursorType.HAND);
		
		IComponent component = Checks.checkNotNull(getModule().getDelegate().getPrimaryComponent(), new ModuleException("Module does not have a primary component"));
		
		addClickListener((event) -> {
			if(event.getButton() == ButtonType.LEFT)
			{
				WindowComponent window = new WindowComponent(getModule().getName(), component);
				window.display();
				window.setMinSize(window.getSize());
			}
		});
		
		setBorder(2, 2);
	}
}
