package net.anasa.util.logic.statement;

import net.anasa.util.logic.ICondition;
import net.anasa.util.logic.IStatement;


public class WhileStatement implements IStatement
{
	private final ICondition condition;
	private final IStatement statement;
	
	public WhileStatement(ICondition condition, IStatement statement)
	{
		this.condition = condition;
		this.statement = statement;
	}
	
	public ICondition getCondition()
	{
		return condition;
	}
	
	public IStatement getStatement()
	{
		return statement;
	}
	
	@Override
	public void run()
	{
		while(getCondition().isValid())
		{
			getStatement().run();
		}
	}
}
