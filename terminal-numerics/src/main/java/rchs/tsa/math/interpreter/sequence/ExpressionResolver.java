package rchs.tsa.math.interpreter.sequence;

import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.NumberHelper;
import net.anasa.util.StringHelper;
import net.anasa.util.StringHelper.NestingException;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.MultiResolver;
import net.anasa.util.data.resolver.ResolverException;
import net.anasa.util.data.resolver.Token;
import net.anasa.util.data.resolver.logic.BiResolver;
import net.anasa.util.data.resolver.logic.CollectorResolver;
import net.anasa.util.data.resolver.logic.IResolver;
import rchs.tsa.math.MathNumber;
import rchs.tsa.math.expression.ConstantType;
import rchs.tsa.math.expression.FunctionExpression;
import rchs.tsa.math.expression.FunctionType;
import rchs.tsa.math.expression.IExpression;
import rchs.tsa.math.expression.IFunction;
import rchs.tsa.math.expression.NumberExpression;
import rchs.tsa.math.expression.OperationExpression;
import rchs.tsa.math.expression.OperatorType;
import rchs.tsa.math.expression.VariableExpression;
import rchs.tsa.math.sequence.SequenceNesting;
import rchs.tsa.math.sequence.TokenType;

public class ExpressionResolver extends MultiResolver<IExpression>
{
	public ExpressionResolver()
	{
		ExpressionResolver expression = this;
		
		add(new BiResolver<>("multiply", new CollectorResolver(expression), new CollectorResolver(expression), (a, b) -> expression.resolve(new Listing<>(a).add(new Token(TokenType.OPERATOR.name(), "*")).addAll(b))));
		
		add(new ITypeResolver<IExpression>()
		{
			@Override
			public TokenType getType()
			{
				return TokenType.NUMBER;
			}
			
			@Override
			public IExpression resolve(IToken item) throws ResolverException
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
		
		add(new ITypeResolver<IExpression>()
		{
			@Override
			public TokenType getType()
			{
				return TokenType.VARIABLE;
			}
			
			@Override
			public IExpression resolve(IToken item) throws ResolverException
			{
				return new VariableExpression(item.getData());
			}
		});
		
		add(new IResolver<IExpression>()
		{
			@Override
			public boolean matches(Listing<IToken> data)
			{
				return data.size() >= 2
						&& TokenType.OPEN_PARENTHESIS.isType(data.get(0).getType())
						&& TokenType.CLOSE_PARENTHESIS.isType(data.get(data.size() - 1).getType())
						&& SequenceNesting.isNestingValid(data.shear(1, 1));
			}
			
			@Override
			public IExpression resolve(Listing<IToken> data) throws ResolverException
			{
				return expression.resolve(data.shear(1, 1));
			}
		});
		
		add(new IResolver<IExpression>()
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
			public IExpression resolve(Listing<IToken> data) throws ResolverException
			{
				try
				{
					Checks.check(SequenceNesting.isNestingValid(data) && SequenceNesting.stripNesting(data).contains((item) -> TokenType.OPERATOR.isType(item.getType())),
							new ResolverException("Invalid operator input data: " + data));
					
					IToken splitter = null;
					
					for(IToken item : SequenceNesting.stripNesting(data))
					{
						if(TokenType.OPERATOR.isType(item.getType()))
						{
							OperatorType op = OperatorType.get(item.getData());
							
							if(splitter == null || !op.hasPriority(OperatorType.get(splitter.getData())))
							{
								splitter = item;
							}
						}
					}
					
					Checks.checkNotNull(splitter, new ResolverException("Sequence must contain an operator"));
					
					int index = data.indexOf(splitter);
					
					IExpression a = expression.resolve(data.subList(0, index));
					IExpression b = expression.resolve(data.subList(index + 1));
					
					return new OperationExpression(OperatorType.get(splitter.getData()), a, b);
				}
				catch(NestingException e)
				{
					throw new ResolverException(e);
				}
			}
		});
		
		add(new BiResolver<>("function", new ITypeResolver<IFunction>()
		{
			@Override
			public TokenType getType()
			{
				return TokenType.FUNCTION;
			}
			
			@Override
			public IFunction resolve(IToken item) throws ResolverException
			{
				return FunctionType.get(item.getData());
			}
		}, expression, (function, operand) -> new FunctionExpression(function, operand)));
		
		add(new BiResolver<>("negative", new ITypeResolver<OperatorType>()
		{
			@Override
			public TokenType getType()
			{
				return TokenType.OPERATOR;
			}
			
			@Override
			public boolean matches(IToken item)
			{
				return StringHelper.equals(OperatorType.SUBTRACT.getSignature(), item.getData());
			}
			
			@Override
			public OperatorType resolve(IToken item) throws ResolverException
			{
				return OperatorType.SUBTRACT;
			}
		}, expression, (negative, operand) -> new OperationExpression(negative, new NumberExpression(new MathNumber(0)), operand)));
	}
}
