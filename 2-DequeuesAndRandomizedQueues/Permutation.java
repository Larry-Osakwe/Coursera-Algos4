
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       4/24/2017
 *  Last updated:  4/24/2017
 *
 *  Compilation:   javac Permutation.java
 *  Execution:     java Permutation
 *  
 *  Takes a command line integer k; reads in a sequence of strings  
 *  from standard input and prints exactly k of them.
 *----------------------------------------------------------------*/
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Larry Osakwe
 */
public class Permutation {

    public static void main(String[] args) {
        
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        String s;
        
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            randQueue.enqueue(s);
        }
        
        Iterator<String> iterator = randQueue.iterator();
        for (int i = 0; i < k; i++) {
            String item = iterator.next();
            StdOut.println(item);
        }
    }
}
