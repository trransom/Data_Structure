/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

/**
 *
 * @author trran
 * @param <T>
 */
public class QueueList<T extends Comparable> implements Queue<T> {

    protected LinkedList<T> list = new LinkedList<>();
   //add constructor that initializes list
    
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return list.getLength();
    }

    @Override
    public T front() {
        return list.retrieve(0);
    }

    @Override
    public T rear() {
        return list.retrieve(list.getLength()-1);
    }

    @Override
    public Queue<T> add(T e) {
        list.append(e);
        return this;
    }

    @Override
    public T remove() {
        T rval = front();
        list.remove(0);
        return rval;
    }

    
}
