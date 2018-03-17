package edu.svu.csc326;

/**
 * Creates an interface for a queue 
 * data structure.
 * @author trran
 * @param <E>
 */
public interface Queue<E> {
    
    public boolean isEmpty();
    
    public boolean isFull();
    
    public int size();
    
    public E front();
    
    public E rear();
    
    public Queue<E> add(E e);
    
    public E remove();
    
   
    
}
