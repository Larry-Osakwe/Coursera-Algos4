/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       4/18/2017
 *  Last updated:  4/19/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats
 *  
 *  Performs Monte Calor Simulation through series of computational steps
 *
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Larry Osakwe
 */
public class PercolationStats {

    private Percolation perky;
    private int trials;
    private double[] threshold;

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N grid size (N-by-N)
     * @param T number of experiments
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Values must be greater than 0");
        }
        this.trials = T;
        this.threshold = new double[T];
        for (int i = 0; i < T; i++) {
            this.perky = new Percolation(N);

            while (!this.perky.percolates()) {
                int row = StdRandom.uniform(1, N + 1);
                int col = StdRandom.uniform(1, N + 1);

                this.perky.open(row, col);

            }
            threshold[i] = (double) perky.numberOfOpenSites() / (double) (N * N);
        }

    }

    /**
     * sample mean of percolation threshold
     *
     *
     *
     * @return 
     */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     *
     *
     * @return 
     */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /**
     * low  endpoint of 95% confidence interval
     *
     *
     *
     * @return 
     */
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev()) / Math.sqrt(trials);
    }

    /**
     * high endpoint of 95% confidence interval
     *
     *
     *
     * @return 
     */
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {

        PercolationStats perkystats = new PercolationStats(200, 100);

        StdOut.println("mean                    = " + perkystats.mean());
        StdOut.println("stddev                  = " + perkystats.stddev());
        StdOut.println("95% confidence interval = [" + perkystats.confidenceLo() + ", "
                + perkystats.confidenceLo() + "]");
    }

}
