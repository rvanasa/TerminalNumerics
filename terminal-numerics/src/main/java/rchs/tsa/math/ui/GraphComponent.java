package rchs.tsa.math.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import net.anasa.util.Bounds;
import net.anasa.util.Checks;
import net.anasa.util.Concurrency;
import net.anasa.util.Debug;
import net.anasa.util.Debug.MessageType;
import net.anasa.util.Listing;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.ui.ContextMenuComponent;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.SliderComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.event.IComponentListener;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.menu.MenuActionComponent;
import rchs.tsa.math.MathException;
import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.graph.Graph;
import rchs.tsa.math.graph.GraphView;
import rchs.tsa.math.interpreter.SequenceParser;
import rchs.tsa.math.ui.event.GraphEvent;
import rchs.tsa.math.util.Evaluator;

public class GraphComponent extends PanelComponent
{
	private static final int SIZE = 320;
	
	private static final Font FONT = new Font(Font.SERIF, Font.PLAIN, 10);
	
	private static final Color BG_COLOR = new Color(0xF8F8F8);
	private static final Color AXIS_COLOR = new Color(0x666666);
	private static final Color INTERVAL_COLOR = new Color(0xAAAAAA);
	
	private final Listing<FunctionColor> functionColors = new Listing<>(new FunctionColor[] {
			new FunctionColor("Red", 0xAA0000),
			new FunctionColor("Blue", 0x0000AA),
			new FunctionColor("Green", 0x009922),
			new FunctionColor("Gold", 0x99690F),
			new FunctionColor("Purple", 0x62138A),
			new FunctionColor("Brown", 0x6B5231),
			new FunctionColor("Turquoise", 0x0F9997),
			new FunctionColor("Pink", 0xA6517D),
	});
	
	private final Listing<Graph> graphs = new Listing<Graph>().setHandle(new CopyOnWriteArrayList<>());
	private final GraphView view;
	
	private final PanelComponent graphPanel;
	
	private final SliderComponent scaleSlider;
	
	private SequenceParser parser;
	
	private MathData mathData = new MathData();
	
	public GraphComponent(String data)
	{
		this();
		
		updateGraphs(data);
	}
	
	public GraphComponent(Graph... graphs)
	{
		this(new GraphView(), graphs);
	}
	
	public GraphComponent(GraphView view, Graph... graphs)
	{
		this.view = view;
		
		setBorder(2, 2);
		
		graphPanel = new PanelComponent();
		graphPanel.addDrawListener((event) -> updateGraphPanel(event.getGraphics()));
		graphPanel.setContextMenu(new ContextMenuComponent(new IComponent[] {
				new MenuActionComponent("Configure", () -> {
						MathDataComponent component = new MathDataComponent(getMathData());
						component.addDrawListener((event) -> redraw());
						addDrawListener((event) -> component.updateVariables());
						new WindowComponent("Graph Configuration", TerminalNumerics.getIcon(), component).setResizable(false).tether(WindowComponent.getParentWindow(this)).display();
					})
		}));
		
		graphPanel.setBackground(BG_COLOR);
		graphPanel.setSize(SIZE, SIZE);
		
		setParser(SequenceParser.EXPRESSION);
		
		scaleSlider = new SliderComponent(0, 154, 40);
		scaleSlider.addActionListener((event) -> redraw());
		
		PanelComponent bottom = new PanelComponent();
		bottom.setBorder(2, 2);
		
		new UIBorderLayout()
				.set(BorderPosition.LEFT, new LabelComponent("Zoom"))
				.set(BorderPosition.CENTER, scaleSlider)
				.apply(bottom);
		
		new UIBorderLayout()
				.set(BorderPosition.CENTER, graphPanel)
				.set(BorderPosition.BOTTOM, bottom)
				.apply(this);
		
		for(Graph graph : graphs)
		{
			addGraph(graph);
		}
	}
	
	public FunctionColor getFunctionColor(int index)
	{
		return functionColors.getOrDefault(index, new FunctionColor("Black", 0x000000));
	}
	
	public PanelComponent getPanel()
	{
		return graphPanel;
	}
	
	public GraphView getView()
	{
		return view;
	}
	
	public SequenceParser getParser()
	{
		return parser;
	}
	
	public void setParser(SequenceParser parser)
	{
		this.parser = parser;
	}
	
	public MathData getMathData()
	{
		return mathData;
	}
	
	public void setMathData(MathData mathData)
	{
		this.mathData = mathData;
	}
	
	public Listing<Graph> getGraphs()
	{
		return graphs;
	}
	
	public void addGraph(Graph graph)
	{
		getGraphs().add(graph);
		redraw();
	}
	
	public void clearGraphs()
	{
		getGraphs().clear();
	}
	
	public void addGraph(Listing<IToken> data)
	{
		addGraphs(new Listing<Listing<IToken>>(data));
	}
	
	public void addGraphs(Listing<Listing<IToken>> data)
	{
		Checks.checkNotNull(data, "data cannot be null");
		
		Concurrency.runThreaded(() -> {
			clearGraphs();
			for(Listing<IToken> sequence : data)
			{
				try
				{
					Graph graph = new Graph(Evaluator.parse(sequence));
					addGraph(graph);
					
					getEvents().dispatch(new GraphEvent(this, graph));
				}
				catch(MathException e)
				{
					Debug.log("Failed to evaluate expression: " + e.getMessage());
				}
			}
			redraw();
		});
	}
	
	public void updateGraphs(String data)
	{
		if(getParser() == null)
		{
			return;
		}
		
		try
		{
			addGraphs(new Listing<>(data.split(";")).filter((item) -> !item.trim().isEmpty()).conform((item) -> getParser().parse(item)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			clearGraphs();
		}
	}
	
	public void setGraph(String data)
	{
		clearGraphs();
		updateGraphs(data);
	}
	
	public void setGraph(Graph graph)
	{
		clearGraphs();
		addGraph(graph);
	}
	
	public void addGraphListener(IComponentListener<GraphEvent> listener)
	{
		getEvents().register(GraphEvent.class, listener);
	}
	
	@Override
	public void redraw()
	{
		getPanel().redraw();
	}
	
	private void updateGraphPanel(Graphics2D g)
	{
		g.setColor(INTERVAL_COLOR);
		g.setFont(FONT);
		
		double sizeX = 5 + getPanel().getWidth() * Math.pow(scaleSlider.getValue(), 2) / Math.pow(320, 2);
		double sizeY = 5 + getPanel().getHeight() * Math.pow(scaleSlider.getValue(), 2) / Math.pow(320, 2);
		
		getView().setBounds(new Bounds(-sizeX, -sizeY, sizeX, sizeY));
		getView().normalizeInterval();
		
		Bounds bounds = getView().getBounds();
		int intervalX = getView().getIntervalX();
		int intervalY = getView().getIntervalY();
		
		for(int x = (int)(bounds.getMinX() / intervalX) * intervalX; x < bounds.getMaxX(); x += intervalX)
		{
			g.drawLine(getX(x), 0, getX(x), getPanel().getHeight());
			g.drawString(String.valueOf(x), getX(x) + 2, getPanel().getHeight() - 2);
		}
		
		for(int y = (int)(bounds.getMinY() / intervalY) * intervalY; y < bounds.getMaxY(); y += intervalY)
		{
			g.drawLine(0, getY(y), getPanel().getWidth(), getY(y));
			g.drawString(String.valueOf(y), 2, getY(y) - 2);
		}
		
		g.setColor(AXIS_COLOR);
		
		g.drawLine(0, getY(0), getPanel().getWidth(), getY(0));
		g.drawLine(getX(0), 0, getX(0), getPanel().getHeight());
		
		int index = 0;
		for(Graph graph : getGraphs())
		{
			try
			{
				g.setColor(getFunctionColor(index).getColor());
				
				Integer lastX = null;
				Integer lastY = null;
				
				Map<Double, Double> map = getView().getValues(graph, getMathData());
				
				for(Entry<Double, Double> entry : map.entrySet())
				{
					double x = entry.getKey();
					double y = entry.getValue();
					
					int posX = getX(x);
					int posY = getY(y);
					
					if(lastY != null && !Double.isNaN(y))
					{
						g.drawLine(lastX, lastY, posX, posY);
					}
					
					lastX = posX;
					lastY = !Double.isNaN(y) ? posY : null;
				}
			}
			catch(MathException e)
			{
				Debug.msg(MessageType.ERROR, "Failed to draw graph data: " + e.getMessage());
			}
			
			index++;
		}
	}
	
	private int getX(double x)
	{
		return (int)(x * getPanel().getWidth() / getView().getBounds().getWidth()) + getPanel().getWidth() / 2;
	}
	
	private int getY(double y)
	{
		return -(int)(y * getPanel().getHeight() / getView().getBounds().getHeight()) + getPanel().getHeight() / 2;
	}
}
