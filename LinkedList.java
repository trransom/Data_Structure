package edu.svu.csc326;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * A doubly linked list
 *
 * @author Thomas Ransom
 * @param <T>
 */
public class LinkedList<T> implements List<T> {

    private int count;
    private ListNode head;
    private ListNode tail;

    private class ListNode {

        private T data;
        private ListNode next;
        private ListNode prev;

        public ListNode(T data) {
            this.data = data;
        }
    }

    /**
     * Test a list to see if it is empty.
     *
     * @return true if the list is empty; and false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Get the length of the list.
     *
     * @return the length of the list.
     */
    @Override
    public int getLength() {
        return count;
    }

    /**
     * Retrieve a given element by index from the list.
     *
     * @param index the index of the desired element.
     * @return the element at the given index.
     */
    @Override
    public T retrieve(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        ListNode ptr;
        if (index == 0) {
            return head.data;
        } else if (index == count) {
            return tail.data;
        } else if (index < (count / 2)) {
            int elemNum = 0;
            ptr = head;
            while (elemNum < index) {
                ptr = ptr.next;
                elemNum++;
            }
        } else {
            int elemNum = count;
            ptr = tail;
            while (elemNum > index) {
                ptr = ptr.prev;
                elemNum--;
            }
        }
        return ptr.data;
    }

    /**
     * Add an element to the beginning of the list.
     *
     * @param element the element to be added.
     * @return a reference to the altered list.
     */
    @Override
     
    public LinkedList<T> prepend(T element) {
        return insert(0, element);
    }

    /**
     * Add an element to the end of the list.
     *
     * @param element the element to be added.
     * @return a reference to the altered list.
     */
    @Override
    public LinkedList<T> append(T element) {
        return insert(count, element);
    }
    
    
   

    /**
     * Insert an element into a particular indexed position of a list.
     *
     * @param index the position of the insertion.
     * @param element the element to insert.
     * @return a reference to the altered list.
     */
    @Override
    public LinkedList<T> insert(int index, T element) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }
        ListNode n = new ListNode(element);
        if (isEmpty()) {
            head = n;
            tail = n;
        } else if (index == 0) {
            n.next = head;
            head.prev = n;
            head = n;
        } else if (index == count) {
            tail.next = n;
            n.prev = tail;
            tail = n;
        } else {
            if (index > count / 2) {
                int i = count - 1;
                ListNode ptr = tail;
                while (i > index) {
                    ptr = ptr.prev;
                    i--;
                }
                ptr.prev.next = n;
                n.next = ptr;
                n.prev = ptr.prev;
                ptr.prev = n;
            } else {

                int i = 0;
                ListNode ptr = head;
                while (i < index) {

                    ptr = ptr.next;
                    ++i;
                }
                ptr.prev.next = n;
                n.next = ptr;
                n.prev = ptr.prev;
                ptr.prev = n;
            }
            
        }
        ++count;
        return this;
    }

    /**
     * Remove an element at a particular position from the list.
     *
     * @param index the position of the element to be removed.
     * @return a reference to the altered list.
     */
    @Override
    public LinkedList<T> remove(int index) {

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            if (count == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        } else {
            ListNode ptr;
            if (index > count / 2) {
                ptr = tail;
                int i = count - 1;
                while (i > index) {
                    ptr = ptr.prev;
                    --i;
                }

            } else {
                ptr = head;
                int i = 0;
                while (i < index) {

                    ptr = ptr.next;
                    ++i;
                }

            }
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
            if (ptr == tail) {
                tail = ptr.prev;
            }
        }
        --count;
        return this;
    }
    
    private class ReverseIterator implements Iterator<T> {
        
        private Stack<T> stack;
        
        public ReverseIterator() {
           // stack = new StackArray<>();
            Iterator<T> it = iterator();
            while(it.hasNext()){
                stack.push(it.next());
            }
        }
 
        @Override
        public boolean hasNext() {
             return !stack.isEmpty();
        }

        @Override
        public T next() {
             T elem = stack.top();
             stack.pop();
             return elem;
        }
        
    }

    /**
     * Replace an element at a given position with another element.
     *
     * @param index the position of the element to be replaced.
     * @param element the element to add to the list.
     * @return a reference to the altered list.
     */
    @Override
    public LinkedList<T> replace(int index, T element) {

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        ListNode n = new ListNode(element);
        if (index == 0) {
            
            if(head.next != null){
            link(n, head.next);
            }
            else{
            n.next = head.next;
            }
            head = n;
            if (count == 1) {
                tail = n;
            }
        } else {
            ListNode ptr;
            if (index > count / 2) {
                ptr = tail;
                int i = count - 1;
                while (i > index) {
                    ptr = ptr.prev;
                    --i;
                }

            } else {
                ptr = head;
                int i = 0;
                while (i < index) {

                    ptr = ptr.next;
                    ++i;
                }
                ptr.prev.next = n;
                n.next = ptr.next;
                ptr.next.prev = n;
                n.prev = ptr.prev;
                if (tail == ptr) {
                    tail = n;
                }
            }

        }
        return this;
    }

    /**
     * Retrieve the index of an element in the list. uses "equals" for the
     * comparison.
     *
     * @param element the element to look for
     * @return the index of the element if found in the list; -1 otherwise.
     */
    @Override
    public int contains(T element) {
    
        int index = 0;
        ListNode ptr = head;
        while (ptr != null) {
            if (ptr.data.equals(element)) {
                return index;
            }
            ++index;
            ptr = ptr.next;
        }
        return -1;
    }
    
    public int containsR(T element){
        return containsR(element, head, 0);
    }
    
    private int containsR(T element, ListNode ptr, int index){
        if(ptr==null) return -1;
        if(ptr.data.equals(element)) return index;
        return containsR(element, ptr.next, index++);
    }

    /*public ReverseIterator<T> reverseIterator() {
        return new ReverseIterator();
    }*/
    
    /**
     * Create an iterator that provides access to the elements on the list in
     * their indexed order.
     *
     * @return an iterator over the elements of the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private ListNode ptr = head;

        /**
         * Tests to see if there are more elements to be returned.
         *
         * @return true if this iterator has more elements; false otherwise.
         */
        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        /**
         * Gets the next element of the list. If this method is called when
         * there isn't a next element (such as when an iterator is created on an
         * empty list, or all the elements of a list have already been returned,
         * it throws a NullPointerException.
         *
         * @return the next element of the list.
         */
        @Override
        public T next() {
            T result = ptr.data;
            ptr = ptr.next;
            return result;
        }
        
        
        public T prev() {
            T result = ptr.data;
            ptr = ptr.prev;
            return result;
        }
    }

    /**
     * Create a stream representing the elements on the list.
     *
     * @return a stream
     */
    @Override
    public Stream<T> stream() {
        T[] array = (T[]) new Object[count];
        Iterator<T> it = new ListIterator();
        int i = 0;
        while (it.hasNext()) {
            array[i++] = it.next();
        }
        return Arrays.stream(array);
    }

    /**
     * Creates a string representation of the list suitable for printing.
     *
     * @return a string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("List(").append(count).append(") = [");
        ListNode ptr = head;
        while (ptr != null) {
            s.append(ptr.data);
            if (ptr != tail) {
                s.append("]");
            }

            ptr = ptr.next;
        }
        s.append("]");
        return s.toString();
    }

    public int getCount() {
        int count = 0;
        ListNode ptr = head;
        while (ptr != null) {
            ++count;
            ptr = ptr.next;
        }
        return count;
    }
    
    public int getCountR() {
        return getCountR(head);
    }
    
    private int getCountR(ListNode ptr) {
        if(ptr == null) return 0;
        return 1 + getCountR(ptr.next);   
        
    }
    private void link(ListNode first, ListNode second){
    first.next = second;
    second.prev = first;
    
    
    }

}
