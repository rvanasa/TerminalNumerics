package net.anasa.util.logic.statement;

import net.anasa.util.logic.ICondition;
import net.anasa.util.logic.IStatement;


public class ForStatement implements IStatement
{
	private final IStatement begin;
	private final ICondition condition;
	private final IStatement step;
	
	private final IStatement statement;
	
	public ForStatement(IStatement begin, ICondition condition, IStatement step, IStatement statement)
	{
		this.begin = begin;
		this.condition = condition;
		this.step = step;
		
		this.statement = statement;
	}
	
	public IStatement getBegin()
	{
		return begin;
	}

	public ICondition getCondition()
	{
		return condition;
	}

	public IStatement getStep()
	{
		return step;
	}

	public IStatement getStatement()
	{
		return statement;
	}

	@Override
	public void run()
	{
		for(getBegin().run(); getCondition().isValid(); getStep().run())
		{
			getStatement().run();
		}
	}
}
