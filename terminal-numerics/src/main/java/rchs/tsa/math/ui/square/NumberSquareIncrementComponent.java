package rchs.tsa.math.ui.square;

import net.anasa.util.ui.ButtonComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class NumberSquareIncrementComponent extends PanelComponent
{
	private final NumberSquareComponent square;
	
	public NumberSquareIncrementComponent(int width, int height)
	{
		this(new NumberSquareComponent(width, height));
	}
	
	public NumberSquareIncrementComponent(NumberSquareComponent square)
	{
		this.square = square;
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.CENTER, square);
		layout.set(BorderPosition.LEFT, new ButtonComponent("-1", () -> square.setX(square.getX() - 1)));
		layout.set(BorderPosition.RIGHT, new ButtonComponent("+1", () -> square.setX(square.getX() + 1)));
		layout.set(BorderPosition.TOP, new ButtonComponent("-1", () -> square.setY(square.getY() - 1)));
		layout.set(BorderPosition.BOTTOM, new ButtonComponent("+1", () -> square.setY(square.getY() + 1)));
		layout.apply(this);
	}

	public NumberSquareComponent getSquare()
	{
		return square;
	}
}
