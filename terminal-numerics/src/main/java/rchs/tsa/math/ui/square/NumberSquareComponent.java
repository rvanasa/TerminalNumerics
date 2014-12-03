package rchs.tsa.math.ui.square;

import java.awt.Font;
import java.awt.Graphics2D;

import net.anasa.util.math.MathHelper;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.PanelComponent;

public class NumberSquareComponent extends PanelComponent
{
	private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final int SQUARE_SIZE = 32;
	
	private final int width, height;
	private final NumberSquare[] squares;
	
	private int x, y, number;
	
	public NumberSquareComponent()
	{
		this(10, 10);
	}
	
	public NumberSquareComponent(int width, int height)
	{
		this(width, height, width, height, width * height);
	}
	
	public NumberSquareComponent(int width, int height, int x, int y, int number)
	{
		this.width = width;
		this.height = height;
		
		squares = new NumberSquare[width * height];
		
		int n = 0;
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				squares[n] = new NumberSquare(i, j, n + 1);
				n++;
			}
		}
		
		setX(x);
		setY(y);
		
		setNumber(number);
		
		setSize(new Vector2(width, height).multiply(SQUARE_SIZE).add(1, 1));
		
		addDrawListener((event) -> draw(event.getGraphics()));
	}
	
	public void update()
	{
		redraw();
	}
	
	protected void draw(Graphics2D graphics)
	{
		graphics.setFont(FONT);
		
		for(NumberSquare square : getSquares())
		{
			if(shouldDraw(square))
			{
				square.draw(graphics);
			}
		}
	}
	
	public boolean shouldDraw(NumberSquare square)
	{
		return getX() >= square.getX() + 1 && getY() >= square.getY() + 1 && getNumber() >= square.getNumber();
	}
	
	@Override
	public int getWidth()
	{
		return width;
	}
	
	@Override
	public int getHeight()
	{
		return height;
	}
	
	public int getSquareSizeX()
	{
		return (int)(getSize().getX() / getWidth());
	}
	
	public int getSquareSizeY()
	{
		return (int)(getSize().getY() / getHeight());
	}
	
	public NumberSquare[] getSquares()
	{
		return squares;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = MathHelper.clampInt(x, 0, getWidth());
		update();
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = MathHelper.clampInt(y, 0, getHeight());
		update();
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setNumber(int number)
	{
		this.number = number;
	}
	
	public class NumberSquare
	{
		private final int x, y;
		private final int number;
		
		protected NumberSquare(int x, int y, int number)
		{
			this.x = x;
			this.y = y;
			
			this.number = number;
		}
		
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
		}
		
		public int getNumber()
		{
			return number;
		}
		
		public void draw(Graphics2D graphics)
		{
			String n = String.valueOf(getNumber());
			
			graphics.drawRect(getX() * getSquareSizeX() + 1, getY() * getSquareSizeY() + 1, getSquareSizeX() - 2, getSquareSizeY() - 2);
			graphics.drawString(n, (getX() + 0.5F) * getSquareSizeX() - graphics.getFontMetrics().stringWidth(n) / 2, getY() * getSquareSizeY() + 6 + (getSquareSizeY() / 2));
		}
	}
}
