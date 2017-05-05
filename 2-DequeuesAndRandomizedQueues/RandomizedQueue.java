/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       4/24/2017
 *  Last updated:  4/24/2017
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  
 *  Similar to stack or queue, except that the item removed is 
 *  chosen uniformly at random from items in the data structure.
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Larry Osakwe
 * @param <Item> fill the queue with item objects
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n;
    

    /**
     * construct an empty randomized queue
     *
     *
     */
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[2];
        this.n = 0;
    }

    /**
     * is the queue empty?
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * return the number of items on the queue
     *
     *
     * @return size
     */
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        
        queue = copy;
    }

    /**
     * add the item
     *
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null item");
        }
        if (n == queue.length) {
            resize(2 * queue.length);
        }
        queue[n++] = item;
        /*if (last == queue.length) {
            last = 0;
        }*/
        
    }

    /**
     * remove and return a random item
     *
     *
     * @return 
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        int rand = StdRandom.uniform(n);
        Item item = queue[rand];
        queue[rand] = null;
        n--;
        rand++;
        if (rand == queue.length) {
            rand = 0;
        }
        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    /**
     * return (but do not remove) a random item
     *
     *
     * @return 
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        int rand = StdRandom.uniform(n);
        return queue[rand];
    }

    /**
     * return an independent iterator over items in random order
     *
     *
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        
        private final Item[] rand;
        private int current;
        
        public RandomQueueIterator() {
            this.rand = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                rand[i] = queue[i];
            }
            
            StdRandom.shuffle(rand);
        }

        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return rand[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }



    public static void main(String[] args) {

    }

}
