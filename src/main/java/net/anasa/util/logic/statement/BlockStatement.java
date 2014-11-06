package net.anasa.util.logic.statement;

import net.anasa.util.logic.IStatement;

public class BlockStatement implements IStatement
{
	private final IStatement[] statements;
	
	public BlockStatement(IStatement[] statements)
	{
		this.statements = statements;
	}

	public IStatement[] getStatements()
	{
		return statements;
	}

	@Override
	public void run()
	{
		for(IStatement statement : getStatements())
		{
			statement.run();
		}
	}
}
