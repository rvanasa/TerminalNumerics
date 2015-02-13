package rchs.tsa.math.graph;

import java.util.LinkedHashMap;
import java.util.Map;

import net.anasa.util.Bounds;
import net.anasa.util.Checks;
import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.MathNumber;

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
	
	public Map<Double, Double> getValues(Graph graph) throws MathException
	{
		Checks.checkNotNull(graph, new MathException("Graph cannot be null"));
		
		Map<Double, Double> map = new LinkedHashMap<>((int)(getBounds().getWidth() * getResolution()), 0.8F);
		
		double min = getBounds().getMinX();
		double max = getBounds().getMaxX();
		
		double step = getBounds().getWidth() / getResolution();
		
		for(double n = min; n <= max; n += step)
		{
			interpolate(graph, map, n, n + step);
		}
		
		return map;
	}
	
	private void interpolate(Graph graph, Map<Double, Double> map, double x1, double x2) throws MathException
	{
		double y1 = graph.getFrom(new MathNumber(x1)).getValue();
		double y2 = graph.getFrom(new MathNumber(x2)).getValue();
		
		double diffX = x2 - x1;
		double diffY = Math.abs(y2 - y1);
		
		if(Double.isInfinite(y1))
		{
			y1 = Double.NaN;
		}
		if(Double.isInfinite(y2))
		{
			y2 = Double.NaN;
		}
		
		if(diffY > diffX && diffX > getBounds().getWidth() / getResolution() * 0.4)
		{
			interpolate(graph, map, x1, x1 + diffX / 2);
			interpolate(graph, map, x1 + diffX / 2, x2);
		}
		else
		{
			map.put(x1, y1);
			
			if(diffY > getBounds().getHeight())
			{
				map.put(x1 + diffX / 2, Double.NaN);
			}
		}
	}
}
