package net.anasa.math.io;

import java.io.IOException;

import net.anasa.math.MathException;
import net.anasa.math.expression.IExpression;
import net.anasa.math.interpreter.IMathParser;
import net.anasa.math.interpreter.SequenceParser;
import net.anasa.util.data.io.IHandler;

public class ExpressionHandler implements IHandler<IExpression>
{
	private final IHandler<String> handler;
	
	private final IMathParser parser;
	
	public ExpressionHandler(IHandler<String> handler)
	{
		this(handler, new SequenceParser());
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
	public IExpression read() throws IOException
	{
		try
		{
			return getParser().getFrom(getHandler().read());
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
