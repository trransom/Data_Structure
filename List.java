package edu.svu.csc326;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author Thomas Ransom
 */
public interface List<T> extends Iterable<T>{

    public boolean isEmpty();

    public int getLength();

    public T retrieve(int index);

    public List<T> prepend(T element);

    public List<T> append(T element);

    public List<T> insert(int index, T element);

    public List<T> remove(int index);

    public List<T> replace(int index, T element);

    public int contains(T element);

    public Iterator<T> iterator();

    public Stream<T> stream();

}
