package edu.svu.csc326;

/**
 *
 * @author trran
 */
public class QueueCirc<T extends Comparable> implements Queue<T> {
    
    private T[] list;
    private int rear;
    private int front;
    private int count;
    
    
    public QueueCirc(){
        this(20);
    }
    
    public QueueCirc(int maxSize){
        list = (T[])(new Comparable[maxSize]);
        front = 0;
        rear = 0;
        count = 0;
    }
        
    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public boolean isFull() {
        return count==list.length;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public T front() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException("Attempt to access the front of an empty queue.");
        }
        return list[front];
    }

    @Override
    public T rear() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException("Attempt to access the rear of an empty queue.");
        }
        return list[rear];
        
    }

    @Override
    public Queue<T> add(T element) {
        if(isFull()){
            throw new IndexOutOfBoundsException("Attempt to add to a full queue.");
        }
        list[rear] = element;
        rear = (rear + 1)%list.length;
        --count;
        return this;
    }

    @Override
    public T remove() {
       T rval = list[front];
        --count;
        return rval;
    }
    
}
