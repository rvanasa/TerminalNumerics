package net.anasa.util.logic.statement;

import net.anasa.util.logic.ICondition;
import net.anasa.util.logic.IStatement;


public class IfStatement implements IStatement
{
	private final ICondition condition;
	private final IStatement statement;
	
	private final IStatement elseStatement;
	
	public IfStatement(ICondition condition, IStatement statement)
	{
		this(condition, statement, new EmptyStatement());
	}
	
	public IfStatement(ICondition condition, IStatement statement, IStatement elseStatement)
	{
		this.condition = condition;
		this.statement = statement;
		
		this.elseStatement = elseStatement;
	}
	
	public ICondition getCondition()
	{
		return condition;
	}
	
	public IStatement getStatement()
	{
		return statement;
	}
	
	public IStatement getElseStatement()
	{
		return elseStatement;
	}
	
	@Override
	public void run()
	{
		if(getCondition().isValid())
		{
			getStatement().run();
		}
		else
		{
			getElseStatement().run();
		}
	}
}
