package edu.svu.csc326;

/**
 *
 * @author trran
 */
public class StackArray<T extends Comparable> implements Stack<T> {
    
    private ArrayList<T> list;

    public StackArray() {
        list = new ArrayList<>();
    }
    
    @Override
    public Stack<T> push(T elem) {
        list.append(elem);
        return this;
    }

    @Override
    public Stack<T> pop() {
        list.remove(list.getLength()-1);
        return this;
    }

    @Override
    public T top() {
        return list.retrieve(list.getLength()-1);
    }

    @Override
    public int size() {
        return list.getLength();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
}
