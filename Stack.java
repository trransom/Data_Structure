package edu.svu.csc326;

/**
 * Interface for a stack,
 * a FILO data structure.
 * @author trran
 */
public interface Stack<E> {
    
    Stack<E> push(E elem);
    Stack<E> pop();
    E top();
    int size();
    boolean isEmpty();
    
}
