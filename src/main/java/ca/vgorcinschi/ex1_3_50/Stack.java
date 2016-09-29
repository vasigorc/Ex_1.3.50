package ca.vgorcinschi.ex1_3_50;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by vgorcinschi on 28/09/16.
 */
public class Stack <Item> implements Iterable<Item> {

    private Node first;//top of the stack (most recently added)
    private int N;
    private int opsCounter;

    private class Node{
        //nested class to define nodes
        Item item;
        Node next;
    }

    public boolean isEmpty(){ return first == null;}

    public int size() { return N;}

    public void push(Item item){
        //Add item to top of the stack
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
        opsCounter++;
    }

    public Item pop(){
        //check if the stack is not empty
        if (isEmpty()){
            throw new RuntimeException("Stack underflow");
        }
        //Remove item from top of stack
        Item item = first.item;
        first = first.next;
        N--;
        opsCounter++;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private int opsCounterSnapshot = opsCounter;
        private Node current = first;

        public boolean hasNext() {
            if (opsCounterSnapshot != opsCounter){
                throw new ConcurrentModificationException();
            }
            return current != null;
        }

        public Item next() {
            if (opsCounterSnapshot != opsCounter){
                throw new ConcurrentModificationException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
