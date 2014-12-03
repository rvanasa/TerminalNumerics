package rchs.tsa.math;

import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.WindowComponent;
import rchs.tsa.math.ui.square.NumberSquareIncrementComponent;

public class TerminalNumericsTest
{
	public static void main(String... argv) throws Exception
	{
		IComponent component = new NumberSquareIncrementComponent(10, 10);
		
		new WindowComponent("Test", component).display();
	}
}
