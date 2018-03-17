package edu.svu.csc326;

import java.util.Iterator;

/**
 *
 * @author trran
 */
public interface Tree<T extends Comparable> extends Iterable<T> {
    
   
    
    int size();
    boolean isEmpty();
    boolean contains(T element);
    Tree<T> add(T element);
    Tree<T> remove(T element);
    Iterator<T> preIterator();
    Iterator<T> postIterator();
    Iterator<T> inIterator();
    
}
