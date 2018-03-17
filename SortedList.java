package edu.svu.csc326;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Creates an Array backed List, or an ArrayList
 * @author Thomas Ransom
 * @param <T>
 */
public class SortedList<T extends Comparable> implements List<T> {
    
    public enum SORT {
        BUBBLE, HEAP, INSERTION, MERGE,
        QUICK, SELECTION, SHELL
    }
    
    private boolean sorted = false;
    private SORT defaultSort = SORT.MERGE;
   
    private T[] list;
    private int length;
  
    
    
    public void sortLoop(SortedList sl) {
        
        for(int i=0; i<=7; i++) 
            
            switch(i) {
                case 0:
                    long a = System.currentTimeMillis();
                    sl.bubbleSort();
                    long b = System.currentTimeMillis();
                    System.out.println("Bubble Sort: " + (b-a));
                    break;
                case 1:
                    long c = System.currentTimeMillis();
                    sl.heapSort();
                    long d = System.currentTimeMillis();
                    System.out.println("Heap Sort: " + (d-c));
                    break;
                case 2:
                    long e = System.currentTimeMillis();
                    sl.insertionSort();
                    long f = System.currentTimeMillis();
                    System.out.println("Insertion Sort: " + (f-e));
                    break;
                case 3:
                    long g = System.currentTimeMillis();
                    sl.mergeSort();
                    long h = System.currentTimeMillis();
                    System.out.println("Merge Sort: " + (h-g));
                    break;
                case 4:
                    long j = System.currentTimeMillis();
                    sl.quickSort();
                    long k = System.currentTimeMillis();
                    System.out.println("Quick Sort: " + (k-j));
                    break;
                case 5:
                    long l = System.currentTimeMillis();
                    sl.selectionSort();
                    long m = System.currentTimeMillis();
                    System.out.println("Selection Sort: " + (m-l));
                    break;
                case 6:
                    long n = System.currentTimeMillis();
                    sl.shellSort();
                    long o = System.currentTimeMillis();
                    System.out.println("Shell Sort: " + (o-n));
                    break;
                    
            }
    }
   /*
    * Constructor which creates an initial ArrayList with 
    * a length of 20
    */
    public SortedList() {
        this(20);
       
    }

    /*
    * Constructor which allows the programmer to create 
    * an ArrayList at the size he or she wants.
    */
    public SortedList(int maxSize) {
        list = (T[]) new Comparable[maxSize];
        length = 0;
    }
    
    //Create a list which is a copy of an existing list.
    public SortedList(SortedList<T> sortedList) {
        this.list = Arrays.copyOf(sortedList.list, sortedList.list.length);
        this.sorted = sortedList.sorted;
        this.defaultSort = sortedList.defaultSort;
        this.length = sortedList.length;
    }
    
    //get the enumerated type of sort that will be used
    //when the general sort() method is called.
    public SORT getDefaultSort() {
        return defaultSort;
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
        sorted = false;
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
        sorted = false;
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
    
    /**
     * Utilizes a binary search algorithm to find
     * the index of a key in the list. If the key
     * is not found in the list, it returns -1.
     * 
     * @param key the value for which to search
     * @return the position of the key in the list or -1.
     */
    public int contain(T key) {
        if (!sorted) {
            selectionSort(); 
        }
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = low + (high - low) /2;
            if (key.compareTo(list[mid]) < 0) {
                high = mid - 1;
            } else if (key.compareTo(list[mid]) > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    private void swap(int i, int j) {
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
    
    /**
     * Performs a selection sort on the elements 
     * of the list
     * @param sl
     * @return 
     */
    public void selectionSort() {
        if (!sorted) {
            for (int i = 0; i < length - 1; i++) {
                int index = i;
                for (int j = i + 1; j < length; j++) {
                    if (list[j].compareTo(list[index]) < 0) {
                        index = j;
                    }
                }
                swap(index, i);
            }
           // sorted = true;
        }
    }
    
    /**
     * Performs an insertion sort on the elements 
     * of the list.
     * @param sl
     * @return 
     */
    public void insertionSort() {
        if (!sorted) {
            for (int i = 1; i < length; i++) {
                int j = i;
                while (j > 0 && list[j].compareTo(list[j-1]) < 0) {
                    swap(j, j-1);
                    --j;
                }
            }
            //sorted = true;
        }
    }
    
    /**
     * Performs a bubble sort on the elements 
     * of the list.
     * @param sl
     * @return 
     */
    public void bubbleSort() {
        if (!sorted) {
            boolean done = false;
            while (!done) {
                done = true;
                for (int i = 0; i < length - 1; i++) {
                    if (list[i].compareTo(list[i + 1]) > 0) {
                        swap(i, i + 1);
                        done = false;
                    }
                }
            }
            //sorted = true;
        }
    }
    
    /**
     * Performs a shell sort on the elements 
     * of the list.
     * @param sl
     * @return 
     */
    public void shellSort() { 
        if (!sorted) {
            for (int g = length-1 / 2; g > 0; g = g / 2) {
                for (int i = g; i <= length-1; ++i) {
                    T temp = list[i];
                    int j = i;
                    while (j >= g && list[j-g].compareTo(temp) > 0) {
                        list[j] = list[j-g];
                        j -= g;
                        //list[j] = temp;//not originally here
                    }
                    list[j] = temp;
                }
            }
            //sorted = true;
        }
    }
    
/**
 * Performs a heap sort on the elements
 * of the list.
     * @param sl
     * @return 
 */
 public void heapSort() {
     if (!sorted) {
         int n = length-1;
         for (int i = n / 2; i >= 0; --i) {
             heapify(i);
         }
         for (int i = n; i > 0; i--) {
             swap(0, i);
             n = n - 1;
             heapify(0);
         }
     
         //sorted = true;
     }
 }

  
 private void heapify(int i) {
     int left = 2 * i;
     int right = 2 * i + 1;
     int max = i;
     if (left <= length - 1 && list[left].compareTo(list[right]) > 0) {
         max = left;
     }
     if (right <= length - 1 && list[right].compareTo(list[max]) > 0) { 
         max = right;
     }
     if (max != i) {
         swap(i, max);
         heapify(max);
     }
 }

 
 /**
  * Performs a quick sort on the elements
  * of the list.
  * @param sl
  * @return 
  */
  public void quickSort() {
      if (!sorted) {
          quickSortPart(0, length - 1); 
          //sorted = true;
      }
  } 
  
  private void quickSortPart(int low, int high) {
      T pivot = (T) list[low + (high - low) / 2]; 
      int i = low;
      int j = high;
      while (i <= j) {
          while (list[i].compareTo(pivot) < 0) {  //<
              i++; 
          }
          while (list[j].compareTo(pivot) > 0) {  //>
              j--;
          }
          if (i <= j) {
              swap(i, j);
              i++;
              j--;
          }
      }
      if (low < j) {
          quickSortPart(low, j);
      }
      if (i < high) {
          quickSortPart(i, high);
      }
  }
  
  
  /**
   * Performs a merge sort on the elements
   * of the list.
     * @param sl
     * @return 
   */
  public void mergeSort() {
      if (!sorted) {
          T[] temp = (T[]) new Comparable[length];
          mergeSortPart(0, length - 1, temp);
          //sorted = true;
      }
  }
  
  private void mergeSortPart(int low, int high, T[] temp) {
      if (low < high) {
          int mid = low + (high - low) / 2;
          mergeSortPart(low, mid, temp);
          mergeSortPart(mid + 1, high, temp);
          merge(low, mid, high, temp);
          
      }
  }
  
  private void merge(int low, int mid, int high, T[] temp) {
      for (int i = low; i <= high; ++i) {
          temp[i] = list[i];
      }
      int i = low;
      int j = mid + 1;
      int k = low;
      while (i <= mid && j <= high) {
          if (temp[i].compareTo(temp[j]) < 0) {
              list[k++] = temp[i++];
          } else {
              list[k++] = temp[j++];
          }
      } 
      while (i <= mid) {
          list[k++] = temp[i++];
      }
  }
  
  

}


