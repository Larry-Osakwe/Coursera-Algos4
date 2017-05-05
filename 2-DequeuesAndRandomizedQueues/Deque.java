/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       4/24/2017
 *  Last updated:  4/24/2017
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *  
 *  A stack and queue that supports adding and removing items from
 *  either the front or back of the data structure.
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * @author Larry Osakwe
 * @param <Item> fill the deque with item objects
 */
public class Deque<Item> implements Iterable<Item> {

    private int n; // size of the stack
    private Node first; // top of stack
    private Node last; // bottom of stack

    /**
     * Inner class Node to be used as helper values
     *
     *
     */
    private class Node {

        private Item item;
        private Node next;
        private Node previous;

    }

    /**
     * Construct an empty deque
     *
     */
    public Deque() {
        this.first = null;
        this.last = null;
        n = 0;
    }

    /**
     * Check to see if deque is empty
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Return the number of items in the deque
     *
     * @return size
     */
    public int size() {
        return n;
    }

    /**
     * add item to front of deque
     *
     *
     * @param item to be added to front
     */
    public void addFirst(Item item) { // push
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.previous = first;
        }
        n++;
    }

    /**
     * add item to end of deque
     *
     * @param item to be added to the end
     */
    public void addLast(Item item) { // enqueue
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;

        } else {
            oldlast.next = last;
        }
        n++;
    }

    /**
     * remove and return the item from the front
     *
     *
     * @return item removed from front
     */
    public Item removeFirst() { // pop
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node second = first.next;
            second.previous = null;
            first.next = null;
            first = second;
        }
        n--;
        return item;
    }

    /**
     * remove and return the item from the end
     *
     *
     * @return item removed from end
     */
    public Item removeLast() { // deque (opposite)
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            Node oldLast = last;
            last = oldLast.previous;
            last.next = null;
            oldLast.previous = null;
        }
        n--;
        return item;

    }

    /**
     * return an iterator over items in order from front to end
     *
     *
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        /*Deque k = new Deque();

        k.addFirst(3);
        k.addFirst(1);
        k.addLast(10);
        k.addFirst(7);
        k.addLast(8);

        k.removeLast();
        k.removeLast();
        k.removeFirst();
        k.removeFirst();

        Iterator<Integer> i = k.iterator();
        while (i.hasNext()) {
            int s = i.next();
            StdOut.println(s);
        }*/

    }

}
