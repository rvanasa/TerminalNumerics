package net.anasa.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.anasa.util.data.DataConform;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public class Listing<E> implements Iterable<E>
{
	private List<E> list = new ArrayList<>();
	
	public <T> Listing(T[] values, IConformHandler<T, E> handler) throws FormatException
	{
		this(DataConform.conform(values, handler));
	}
	
	@SafeVarargs
	public Listing(E... values)
	{
		this(Arrays.asList(values));
	}
	
	public Listing(Listing<E> values)
	{
		this(values == null ? null : values.getValues());
	}
	
	public Listing(Collection<E> values)
	{
		if(values != null)
		{
			addAll(values);
		}
	}
	
	public Listing()
	{
		
	}
	
	public List<E> getValues()
	{
		return list;
	}
	
	public Listing<E> setHandle(List<E> handle)
	{
		Checks.checkNotNull(handle, new NullPointerException("Handle cannot be null"));
		this.list = handle;
		
		return this;
	}
	
	public E get(int index)
	{
		return getValues().get(index);
	}
	
	public E getFirst(IListCondition<E> condition)
	{
		for(E element : this)
		{
			if(condition.isValid(element))
			{
				return element;
			}
		}
		
		return null;
	}
	
	public Listing<E> getAll(IListCondition<E> condition)
	{
		return new Listing<>(this).filter(condition);
	}
	
	public E getOrDefault(int index, E def)
	{
		return (index < 0 || index >= size() || get(index) == null) ? def : get(index);
	}
	
	public Listing<E> set(int index, E element)
	{
		ensureIndex(index + 1);
		getValues().set(index, element);
		
		return this;
	}
	
	public E remove(E element)
	{
		getValues().remove(element);
		
		return element;
	}
	
	public Listing<E> removeAll(Collection<E> elements)
	{
		getValues().removeAll(elements);
		
		return this;
	}
	
	public E removeFirst()
	{
		return getValues().remove(0);
	}
	
	public E removeLast()
	{
		return getValues().remove(size() - 1);
	}
	
	public Listing<E> sort(Comparator<E> comparator)
	{
		getValues().sort(comparator);
		
		return this;
	}
	
	public Listing<E> shear(int first, int last)
	{
		return subList(first, size() - last);
	}
	
	public Listing<E> subList(int i)
	{
		return subList(i, size());
	}
	
	public Listing<E> subList(int i, int j)
	{
		return new Listing<>(getValues().subList(i, j));
	}
	
	public int indexOf(E element)
	{
		return getValues().indexOf(element);
	}
	
	public int indexOf(IListCondition<E> condition)
	{
		for(int i = 0; i < size(); i++)
		{
			if(condition.isValid(get(i)))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean check(IListCondition<E> condition)
	{
		for(E element : this)
		{
			if(!condition.isValid(element))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkEach(IListCondition<E> condition)
	{
		boolean flag = true;
		
		for(E element : this)
		{
			if(!condition.isValid(element))
			{
				flag = false;
			}
		}
		
		return flag;
	}
	
	public int count(IListCondition<E> condition)
	{
		int ct = 0;
		
		for(E element : this)
		{
			if(condition.isValid(element))
			{
				ct++;
			}
		}
		
		return ct;
	}
	
	public int size()
	{
		return getValues().size();
	}
	
	public void ensureIndex(int index)
	{
		while(size() < index)
		{
			add(null);
		}
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public boolean contains(E element)
	{
		return getValues().contains(element);
	}
	
	public boolean contains(IListCondition<E> condition)
	{
		for(E element : this)
		{
			if(condition.isValid(element))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Listing<E> add(E element)
	{
		getValues().add(element);
		
		return this;
	}
	
	public Listing<E> add(int index, E element)
	{
		ensureIndex(index);
		getValues().add(index, element);
		
		return this;
	}
	
	public Listing<E> addAll(E[] elements)
	{
		return addAll(Arrays.asList(elements));
	}
	
	public Listing<E> addAll(Iterable<E> elements)
	{
		for(E element : elements)
		{
			add(element);
		}
		
		return this;
	}
	
	public void clear()
	{
		getValues().clear();
	}
	
	public Listing<E> filter(IListCondition<E> condition)
	{
		Collection<E> filter = new HashSet<>();
		
		for(E element : this)
		{
			if(!condition.isValid(element))
			{
				filter.add(element);
			}
		}
		
		getValues().removeAll(filter);
		
		return this;
	}
	
	public Listing<E> compute(IListOperation<E> operation)
	{
		for(int i = 0; i < size(); i++)
		{
			set(i, operation.compute(get(i)));
		}
		
		return this;
	}
	
	public <T> Listing<T> conform(IConformHandler<E, T> handler)
	{
		Listing<T> list = new Listing<>();
		
		for(int i = 0; i < size(); i++)
		{
			try
			{
				list.set(i, handler.getFrom(get(i)));
			}
			catch(FormatException e)
			{
				Debug.err("Failed to conform data: " + get(i) + " (" + e + ")");
			}
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public E[] toArray(Class<E> clazz)
	{
		return getValues().toArray((E[])Array.newInstance(clazz, 0));
	}
	
	public Class<?>[] getTypes()
	{
		Class<?>[] data = new Class<?>[getValues().size()];
		
		for(int i = 0; i < data.length; i++)
		{
			E entry = get(i);
			
			data[i] = entry != null ? entry.getClass() : null;
		}
		
		return data;
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return getValues().iterator();
	}
	
	@Override
	public String toString()
	{
		return getValues().toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return getValues().equals(obj);
	}
	
	public interface IListCondition<E>
	{
		public boolean isValid(E element);
	}
	
	public interface IListAction<E>
	{
		public void act(E element);
	}
	
	public interface IListOperation<E>
	{
		public E compute(E element);
	}
}
