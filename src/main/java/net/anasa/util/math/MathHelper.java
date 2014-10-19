package net.anasa.util.math;

import java.util.List;
import java.util.Random;

public final class MathHelper
{
	private static final Random RANDOM = new Random();
	
	public static Random getRandom()
	{
		return RANDOM;
	}
	
	public static boolean isContained(double n, double a, double b)
	{
		return n >= Math.min(a, b) && n <= Math.max(a, b);
	}
	
	public static int random(int n)
	{
		return getRandom().nextInt(n);
	}
	
	public static float randomF()
	{
		return getRandom().nextFloat();
	}
	
	public static float randomF(float n)
	{
		return randomF() * n;
	}
	
	public static double randomD()
	{
		return getRandom().nextDouble();
	}
	
	public static double randomD(double n)
	{
		return randomD() * n;
	}
	
	public static double randomRadian()
	{
		return randomD(2 * Math.PI);
	}
	
	public static int randomDegree()
	{
		return random(360);
	}
	
	public static double randomD(double a, double b)
	{
		return randomD(Math.abs(a - b)) + Math.min(a, b);
	}
	
	public static float randomF(float a, float b)
	{
		return randomF(Math.abs(a - b)) + Math.min(a, b);
	}
	
	public static int random(int a, int b)
	{
		return random(Math.abs(a - b)) + Math.min(a, b);
	}
	
	public static <T> T random(List<T> list)
	{
		if(list.size() == 0)
		{
			return null;
		}
		
		return list.get(random(list.size()));
	}
	
	public static <T> T random(T[] array)
	{
		if(array.length == 0)
		{
			return null;
		}
		
		return array[random(array.length)];
	}
	
	public static double clampDouble(double n, double a, double b)
	{
		double x = Math.min(a, b);
		double y = Math.max(a, b);
		
		return Math.max(x, Math.min(n, y));
	}
	
	public static int clampInt(int n, int a, int b)
	{
		return (int)clampDouble(n, a, b);
	}
	
	public static int lcm(int a, int b)
	{
		for(int i = Math.min(a, b); i <= a * b; i++)
		{
			if(a % i == 0 && b % i == 0)
			{
				return i;
			}
		}
		
		return a * b;
	}
}
