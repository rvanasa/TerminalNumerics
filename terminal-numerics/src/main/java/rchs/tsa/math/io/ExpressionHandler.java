package rchs.tsa.math.io;

import java.io.IOException;

import rchs.tsa.math.MathException;
import rchs.tsa.math.expression.IExpression;
import rchs.tsa.math.interpreter.IMathParser;
import rchs.tsa.math.interpreter.SequenceParser;
import net.anasa.util.data.io.IHandler;

public class ExpressionHandler implements IHandler<IExpression>
{
	private final IHandler<String> handler;
	
	private final IMathParser parser;
	
	public ExpressionHandler(IHandler<String> handler)
	{
		this(handler, SequenceParser.EXPRESSION);
	}
	
	public ExpressionHandler(IHandler<String> handler, IMathParser parser)
	{
		this.handler = handler;
		this.parser = parser;
	}
	
	public IHandler<String> getHandler()
	{
		return handler;
	}

	public IMathParser getParser()
	{
		return parser;
	}
	
	@Override
	public IExpression load() throws IOException
	{
		try
		{
			return getParser().getFrom(getHandler().load());
		}
		catch(MathException e)
		{
			throw new IOException("Failed to parse expression", e);
		}
	}
	
	@Override
	public void write(IExpression data) throws IOException
	{
		getHandler().write(data.getStringValue());
	}
}
