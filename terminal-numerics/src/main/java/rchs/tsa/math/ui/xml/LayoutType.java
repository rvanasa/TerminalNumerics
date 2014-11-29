package rchs.tsa.math.ui.xml;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.layout.ILayout;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIFlowLayout;
import net.anasa.util.ui.layout.UIVerticalLayout;

public enum LayoutType
{
	FLOW((props) -> {
		UIFlowLayout layout = new UIFlowLayout();
		return layout;
	}),
	VERTICAL((props) -> {
		UIVerticalLayout layout = new UIVerticalLayout();
		layout.setSpacing(props.getInt("spacing", 0));
		return layout;
	}),
	BORDER((props) -> {
		UIBorderLayout layout = new UIBorderLayout();
		layout.setPadding(props.getInt("padding", 0));
		return layout;
	});
	
	private final ILayoutTypeHandle handle;
	
	private LayoutType(ILayoutTypeHandle handle)
	{
		this.handle = handle;
	}
	
	public ILayout getLayout(Properties props) throws FormatException
	{
		return handle.getLayout(props);
	}
	
	private interface ILayoutTypeHandle
	{
		public ILayout getLayout(Properties props) throws FormatException;
	}
}
