package edu.svu.csc326;

import java.util.Iterator;

/**
 *
 * @author trran
 */
public class BinaryTree<T extends Comparable> implements Tree<T> {

    private Node root = null;
    private int count = 0;

    class Node {

        T data;
        Node parent;
        Node lchild;
        Node rchild;

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node parent) {
            this.data = data;
            this.parent = parent;
            lchild = null;
            rchild = null;
        }
        
        public String toString(){
            return "(" + data + " "+ lchild + " " + rchild + ")";
        }

    }

    /**
     * Returns the size of the binary tree.
     * @return 
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns true if the tree is empty.
     * @return 
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns true if the tree contains the element
     * @param element
     * @return 
     */
    @Override
    public boolean contains(T element) {
        return contains(element, root);
    }
    
    /**
     * Returns the data of the root.
     * @return 
     */
    public T getRoot(){
        return root.data;
    }

    private boolean contains(T element, Node n) {
        if (n == null) {
            return false;
        }
        if (n.data.compareTo(element) == 0) {
            return true;
        }
        if (n.data.compareTo(element) > 0) {
            return contains(element, n.lchild);
        } else {
            return contains(element, n.rchild);
        }
    }
    
    /**
     * Adds an element to the tree.
     * @param element
     * @return 
     */
    public Tree<T> add(T element) {
        if(root==null){
            root = new Node(element);
            return this;
        }
        return add(element, root);
    }

    private Tree<T> add(T element, Node n) {
        if (n.data.compareTo(element) == 0) {
            //duplicate node
            return this;
        } else if (n.data.compareTo(element) > 0) {
            if (n.lchild == null) {
                n.lchild = new Node(element, n);
                count++;
                mbalance();
                return this;
            } else {
                return add(element, n.lchild);
            }
        } else {
            if (n.rchild == null) {
                n.rchild = new Node(element, n);
                count++;
                mbalance();
                return this;
            } else {
                return add(element, n.rchild);
            }
        }
    }
    
    /**
     * Checks to see if the two ints are either 
     * both negative or both positive.
     * @param uno
     * @param dos
     * @return 
     */
    public boolean negpos(int uno, int dos){
        if(uno>0 && dos > 0){
            return true;
        } else if(uno<0 && dos <0){
            return true;
        } else return false;
    }
    
    private Tree<T> rebalance(Node n, int i){
        if(i==-2 || i==2){
           if(i<0){
               int next = balanced(n.lchild);
               if(i==next){
                  rebalance(n.lchild, next); 
               } else if(negpos(i, next)){
                   rotateRight(n.lchild);
               } 
              rebalance(n.lchild, next);
          } else if(i>0){
              int next = balanced(n.rchild);
               if(i==next){
                  rebalance(n.rchild, next); 
               } else if(negpos(i, next)){
                   rotateLeft(n.rchild);
               } 
              rebalance(n.rchild, next);
          } 
        } 
        int t = balanced(root);
        if(t==-2 || t==2){
            if(t>0){
                rotateRight(n);
            } else rotateLeft(n);
        } 
        return this;
    }
    
    private Node rotateLeft(Node t){
        Node temp = t.rchild;
        t.rchild = temp.lchild;
        temp.lchild = t;
        t = temp;
        return t; 
    }
    
    private Node rotateRight(Node t){
        Node temp = t.lchild;
        t.lchild = temp.rchild;
        temp.rchild = t;
        t = temp;
        return t;
    }
    
    /**
     * Removes an element from the tree.
     * @param element
     * @return 
     */
    @Override
    public Tree<T> remove(T element) {
        if(!contains(element)){
            return this;
        }
        return this;
    }
    
    /**
     * Returns the depth of the tree
     * @return 
     */
    public int depth(){
        return depth(root);
    }
    
    private int depth(Node n){
        if(n==null){
            return 0;
        }
        return 1 + Math.max(depth(n.lchild), depth(n.rchild));
    }
    
    /**
     * Returns an int which can signify
     * whether or not the tree is balanced.
     * @return 
     */
    public int balanced(){
        return balanced(root);
    }
    
    private int balanced(Node n){
        if(n==null) return 0;
        int test = (Math.abs(depth(n.lchild)-depth(n.rchild)));
        return test;
    }

    private void mbalance(){
       int test = balanced();
       if(test>1 || test<-1){
           rebalance(root, test);
       }
    }
    
    /**
     * Traverses the tree in preorder fashion
     * @return 
     */
    @Override
    public Iterator<T> preIterator() {
        return new PreOrderIterator();
    }

    /**
     * Traverses the tree in postorder fashion
     * @return 
     */
    @Override
    public Iterator<T> postIterator() {
        return new PostOrder();
    }

    /**
     * Traverses the tree in inorder fashion
     * @return 
     */
    @Override
    public Iterator<T> inIterator() {
        return new InOrderIterator();
    }

    /**
     * Returns an inIterator iterator
     * @return 
     */
    @Override
    public Iterator<T> iterator() {
        return inIterator();
    }
    
    /**
     * Returns the root as a string
     * @return 
     */
    public String toString(){
        return root.toString();
    }
    
    /**
     * Inner class implementing the inorder iterator
     */
    class InOrderIterator implements Iterator<T> {
        Queue<T> nodes = new QueueList<>();
        
        public InOrderIterator(){
            addNode(root);
        }
        
        void addNode(Node n){
            if(n!=null){
            addNode(n.lchild);
            nodes.add(n.data);
            addNode(n.rchild);
            }
        }

        @Override
        public boolean hasNext() {
           return !nodes.isEmpty(); 
        }

        @Override
        public T next() {
            return nodes.remove();
        }
        
    }
    
    /**
     * Inner class implementing the preorder iterator
     */
    class PreOrderIterator implements Iterator<T>{
        
        Queue<T> nodes = new QueueList<>();
        
        public PreOrderIterator(){
            addNode(root);
        }
        
        void addNode(Node n){
            if(n!=null){
            
            nodes.add(n.data);
            addNode(n.lchild);
            addNode(n.rchild);
            }
        }

        @Override
        public boolean hasNext() {
           return !nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.remove();
        }
        
    }

    /**
     * Inner class implementing the postorder iterator
     */
    class PostOrder implements Iterator<T> {
         Queue<T> nodes = new QueueList<>();
        
        public PostOrder(){
            addNode(root);
        }
        
        void addNode(Node n){
            if(n!=null){
            addNode(n.lchild);
            addNode(n.rchild);
            nodes.add(n.data);
            }
        }

        @Override
        public boolean hasNext() {
           return !nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.remove();
        }
    }

    

}
