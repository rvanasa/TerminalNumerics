package rchs.tsa.math.graph;

import rchs.tsa.math.MathException;
import rchs.tsa.math.MathNumber;
import net.anasa.util.Bounds;
import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.Mapping;

public class GraphView
{
	private static final int GRID_SCALE = 20;
	
	private Bounds bounds;
	private int resolution;
	private int intervalX;
	private int intervalY;
	
	public GraphView()
	{
		this(new Bounds(-10, -10, 10, 10));
	}
	
	public GraphView(Bounds bounds)
	{
		this(bounds, 800);
		
		normalizeInterval();
	}
	
	public GraphView(Bounds bounds, int resolution)
	{
		setBounds(bounds);
		setResolution(resolution);
	}
	
	public Bounds getBounds()
	{
		return bounds;
	}
	
	public GraphView setBounds(Bounds bounds)
	{
		this.bounds = bounds;
		
		return this;
	}
	
	public void normalizeInterval()
	{
		setIntervalX((int)Math.ceil(getBounds().getWidth() / GRID_SCALE));
		setIntervalY((int)Math.ceil(getBounds().getHeight() / GRID_SCALE));
	}
	
	public int getResolution()
	{
		return resolution;
	}
	
	public GraphView setResolution(int resolution)
	{
		this.resolution = resolution;
		return this;
	}
	
	public int getIntervalX()
	{
		return intervalX;
	}
	
	public GraphView setIntervalX(int intervalX)
	{
		this.intervalX = intervalX;
		return this;
	}
	
	public int getIntervalY()
	{
		return intervalY;
	}
	
	public GraphView setIntervalY(int intervalY)
	{
		this.intervalY = intervalY;
		return this;
	}
	
	public Listing<MathNumber> getInputValues(Graph graph)
	{
		Listing<MathNumber> list = new Listing<>();
		
		double min = getBounds().getMinX();
		double max = getBounds().getMaxX();
		
		for(double n = min; n <= max; n += (max - min) / getResolution())
		{
			list.add(new MathNumber(n));
		}
		
		return list;
	}
	
	public Mapping<MathNumber, MathNumber> getValues(Graph graph) throws MathException
	{
		Checks.checkNotNull(graph, new NullPointerException("Graph cannot be null"));
		
		Mapping<MathNumber, MathNumber> values = new Mapping<>();
		
		Double lastX = null;
		Double lastY = null;
		for(MathNumber x : getInputValues(graph))
		{
			lastY = resolve(graph, x, values, lastX, lastY);
			lastX = x.getValue();
		}
		
		return values;
	}
	
	private double resolve(Graph graph, MathNumber x, Mapping<MathNumber, MathNumber> values, Double lastX, Double lastY) throws MathException
	{
		MathNumber y = graph.getFrom(x);
		
		if(lastX != null && x.getValue() > 0 && lastX < 0)
		{
			values.put(new MathNumber(0), graph.getFrom(new MathNumber(0)));
		}
		
		boolean flag = lastY != null && Math.abs(y.getValue() - lastY) > Math.pow(getBounds().getWidth(), 2) / getResolution() && Math.abs(y.getValue() - lastY) < getBounds().getHeight();
		
		if(flag)
		{
			resolve(graph, new MathNumber(x.getValue() - ((x.getValue() - lastX) / 2)), values, lastX, y.getValue());
		}
		
		if(!y.isNaN())
		{
			values.put(x, y);
		}
		
		if(flag)
		{
			resolve(graph, new MathNumber(x.getValue() + ((x.getValue() - lastX) / 2)), values, x.getValue(), y.getValue());
		}
		
		return y.getValue();
	}
}
