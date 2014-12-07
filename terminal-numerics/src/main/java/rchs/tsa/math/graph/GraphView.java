package rchs.tsa.math.graph;

import net.anasa.util.Bounds;
import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.Mapping;
import rchs.tsa.math.MathException;
import rchs.tsa.math.MathNumber;

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
		this(bounds, 200);
		
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
		
		for(double n = min; n <= max; n += getBounds().getWidth() / getResolution())
		{
			list.add(new MathNumber(n));
		}
		
		return list;
	}
	
	public Mapping<Double, Double> getValues(Graph graph) throws MathException
	{
		Checks.checkNotNull(graph, new NullPointerException("Graph cannot be null"));
		
		Mapping<Double, Double> values = new Mapping<>();
		
		Double lastX = null;
		Double lastY = null;
		for(MathNumber x : getInputValues(graph))
		{
			lastY = getValue(graph, x, values, lastX, lastY);
			lastX = x.getValue();
		}
		
		return values;
	}
	
	protected double getValue(Graph graph, MathNumber x, Mapping<Double, Double> values, Double lastX, Double lastY) throws MathException
	{
		Double y = graph.getFrom(x).getValue();
		
		if(lastX != null && x.getValue() > 0 && lastX < 0)
		{
			values.put(0D, graph.getFrom(new MathNumber(0)).getValue());
		}
		
		boolean flag = lastY != null && Math.abs(y - lastY) > 2 * getBounds().getWidth() / getResolution() && Math.abs(y - lastY) < getBounds().getHeight();
		
		if(flag)
		{
			getValue(graph, new MathNumber(x.getValue() - ((x.getValue() - lastX) / 2)), values, lastX, y);
		}
		
		if(!y.isNaN())
		{
			values.put(x.getValue(), y);
		}
		
		if(flag)
		{
			getValue(graph, new MathNumber(x.getValue() + ((x.getValue() - lastX) / 2)), values, x.getValue(), y);
		}
		
		return y;
	}
}
