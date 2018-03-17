package edu.svu.csc326;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Creates an Array backed List, or an ArrayList
 * @author Thomas Ransom
 * @param <T>
 */
public class ArrayList<T extends Comparable> implements List<T> {

    private T[] list;
    private int length;

   /*
    * Constructor which creates an initial ArrayList with 
    * a length of 20
    */
    public ArrayList() {
        this(20);
    }

    /*
    * Constructor which allows the programmer to create 
    * an ArrayList at the size he or she wants.
    */
    public ArrayList(int maxSize) {
        list = (T[]) new Comparable[maxSize];
        length = 0;
    }

    /*
     * Returns true if there is nothing in the ArrayList
     */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /*
     * Returns the length of the ArrayList
    */
    @Override
    public int getLength() {
        return length;
    }

    /*
    * Retrieves the object at the given index
    */
    @Override
    public T retrieve(int index) {
        if(index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    /*
    * Inserts an object at the beginning of the ArrayList
    */
    @Override
    public List<T> prepend(T element) {
        return insert(0, element);
    }

    /*
    * Inserts an object at the end of the ArrayList
    */
    @Override
    public List<T> append(T element) {
        return insert(length, element);
    }

    /*
    * Inserts an object at the specified index
    */
    @Override
    public List<T> insert(int index, T element) {
        if (length == list.length) {
            resize();
        }
        for (int i = length; i > index; --i) {
            list[i] = list[i - 1];
        }
        list[index] = element;
        ++length;
        return this;
    }

    /*
    * Removes the object at the specified index
    */
    @Override
    public List<T> remove(int index) {
        for (int i = index; i < length - 1; ++i) {
            list[i] = list[i + 1];
        }
        --length;
        return this;
    }

    /*
    * Replaces the object at the specified index with the object
    * 'element'.
    */
    @Override
    public List<T> replace(int index, T element) {
        list[index] = element;
        return this;
    }

    /*
    * Returns the index of the specified element
    */
    @Override
    public int contains(T element) {
        for (int i = 0; i < length; ++i) {
            if (list[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /*
    * Iterates over the ArrayList
    */
    @Override
    public Iterator<T> iterator() {
        return (new Iterator<T>() {
            private int next = 0;

            @Override
            public boolean hasNext() {
                return next < length;
            }

            @Override
            public T next() {
                return list[next++];
            }
        });
    }

    /*
    * "Streams" a copy of the ArrayList
    */
    @Override
    public Stream<T> stream() {
        return Arrays.stream(list, 0, length);
    }

    /*
    * Resizes the ArrayList by doubling it in size.
    */
    //TODO This didn't work in the JUnit testing.
    public void resize() {
        list = Arrays.copyOf(list, list.length * 2);
    }

}
