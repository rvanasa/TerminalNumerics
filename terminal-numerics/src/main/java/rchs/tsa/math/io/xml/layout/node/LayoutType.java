package rchs.tsa.math.io.xml.layout.node;

import net.anasa.util.data.FormatException;
import net.anasa.util.data.IConformHandler;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.layout.ILayout;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIFlowLayout;
import net.anasa.util.ui.layout.UIFlowLayout.FlowType;
import net.anasa.util.ui.layout.UIVerticalLayout;

public enum LayoutType
{
	FLOW((props) -> {
		UIFlowLayout layout = new UIFlowLayout();
		layout.setType(props.getEnum("align", FlowType.CENTER));
		return layout;
	}),
	VERTICAL((props) -> {
		UIVerticalLayout layout = new UIVerticalLayout();
		layout.setSpacing(props.getInt("spacing", 0));
		return layout;
	}),
	BORDER((props) -> {
		UIBorderLayout layout = new UIBorderLayout();
		layout.setPadding(props.getInt("spacing", 0));
		return layout;
	});
	
	private final IConformHandler<Properties, ILayout> handle;
	
	private LayoutType(IConformHandler<Properties, ILayout> handle)
	{
		this.handle = handle;
	}
	
	public ILayout getLayout(Properties props) throws FormatException
	{
		return handle.getFrom(props);
	}
}
