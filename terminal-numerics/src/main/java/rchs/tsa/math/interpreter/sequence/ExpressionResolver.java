package rchs.tsa.math.interpreter.sequence;

import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.NumberHelper;
import net.anasa.util.StringHelper;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.MultiResolver;
import net.anasa.util.data.resolver.ResolverException;
import net.anasa.util.data.resolver.Token;
import net.anasa.util.data.resolver.logic.BiResolver;
import net.anasa.util.data.resolver.logic.CollectorResolver;
import net.anasa.util.data.resolver.logic.IResolver;
import rchs.tsa.math.expression.ConstantType;
import rchs.tsa.math.expression.FunctionExpression;
import rchs.tsa.math.expression.FunctionType;
import rchs.tsa.math.expression.IMathExpression;
import rchs.tsa.math.expression.MathNumber;
import rchs.tsa.math.expression.NumberExpression;
import rchs.tsa.math.expression.OperationExpression;
import rchs.tsa.math.expression.OperatorType;
import rchs.tsa.math.expression.VariableExpression;
import rchs.tsa.math.sequence.ExpressionTokenType;
import rchs.tsa.math.sequence.SequenceNesting;

public class ExpressionResolver extends MultiResolver<IMathExpression>
{
	public ExpressionResolver()
	{
		ExpressionResolver expression = this;
		
		add(new ITypeResolver<IMathExpression>()
		{
			@Override
			public ExpressionTokenType getType()
			{
				return ExpressionTokenType.NUMBER;
			}
			
			@Override
			public IMathExpression resolve(IToken item) throws ResolverException
			{
				String data = item.getData();
				
				ConstantType constant = ConstantType.get(data);
				if(constant != null)
				{
					return new NumberExpression(new MathNumber(constant.getValue()));
				}
				
				Checks.check(NumberHelper.isDouble(data), new ResolverException("Invalid number: " + item.getData()));
				return new NumberExpression(new MathNumber(NumberHelper.getDouble(data)));
			}
		});
		
		add(new ITypeResolver<IMathExpression>()
		{
			@Override
			public ExpressionTokenType getType()
			{
				return ExpressionTokenType.VARIABLE;
			}
			
			@Override
			public IMathExpression resolve(IToken item) throws ResolverException
			{
				return new VariableExpression(item.getData());
			}
		});
		
		add(new IResolver<IMathExpression>()
		{
			@Override
			public boolean matches(Listing<IToken> data)
			{
				return data.size() >= 2
						&& data.get(0).getType() == ExpressionTokenType.OPEN_PARENTHESIS
						&& data.get(data.size() - 1).getType() == ExpressionTokenType.CLOSE_PARENTHESIS
						&& SequenceNesting.isNestingValid(data.shear(1, 1));
			}
			
			@Override
			public IMathExpression resolve(Listing<IToken> data) throws ResolverException
			{
				return expression.resolve(data.shear(1, 1));
			}
		});
		
		IResolver<IMathExpression> operation = new IResolver<IMathExpression>()
		{
			@Override
			public boolean matches(Listing<IToken> data)
			{
				try
				{
					resolve(data);
					return true;
				}
				catch(ResolverException e)
				{
					return false;
				}
			}
			
			@Override
			public IMathExpression resolve(Listing<IToken> data) throws ResolverException
			{
				try
				{
					Checks.check(SequenceNesting.isNestingValid(data) && SequenceNesting.stripNesting(data).contains((item) -> item.getType() == ExpressionTokenType.OPERATOR),
							new ResolverException("Sequence does not contain a valid operator: " + data));
					
					IToken splitter = null;
					
					boolean last = false;
					for(IToken item : SequenceNesting.stripNesting(data))
					{
						if(!last && item.getType() == ExpressionTokenType.OPERATOR)
						{
							OperatorType op = OperatorType.get(item.getData());
							
							if(splitter == null || !op.hasPriority(OperatorType.get(splitter.getData())))
							{
								splitter = item;
								last = true;
							}
						}
						else
						{
							last = false;
						}
					}
					
					int index = data.indexOf(splitter);
					
					IMathExpression a = expression.resolve(data.subList(0, index));
					IMathExpression b = expression.resolve(data.subList(index + 1));
					
					Debug.log(new OperationExpression(OperatorType.get(splitter.getData()), a, b).getStringValue());
					
					return new OperationExpression(OperatorType.get(splitter.getData()), a, b);
				}
				catch(FormatException e)
				{
					throw new ResolverException(e);
				}
			}
		};
		
		add(operation);
		
		add(new BiResolver<>(new CollectorResolver(expression), new CollectorResolver(expression), (a, b) -> expression.resolve(new Listing<>(a).add(new Token(ExpressionTokenType.OPERATOR, "*")).addAll(b))));
		
		add(new IResolver<IMathExpression>()
		{
			@Override
			public boolean matches(Listing<IToken> data)
			{
				return data.size() > 1
						&& data.get(0).getType() == ExpressionTokenType.FUNCTION
						&& expression.matches(data.subList(1));
			}
			
			@Override
			public IMathExpression resolve(Listing<IToken> data) throws ResolverException
			{
				return new FunctionExpression(FunctionType.get(data.get(0).getData()), expression.resolve(data.subList(1)));
			}
		});
		
		add(new IResolver<IMathExpression>()
		{
			@Override
			public boolean matches(Listing<IToken> data)
			{
				return data.size() > 1
						&& StringHelper.equals(OperatorType.SUBTRACT.getSignature(), data.get(0).getData())
						&& expression.matches(data.subList(1));
			}
			
			@Override
			public IMathExpression resolve(Listing<IToken> data) throws ResolverException
			{
				return new OperationExpression(OperatorType.SUBTRACT, new NumberExpression(0), expression.resolve(data.subList(1)));
			}
		});
	}
}
