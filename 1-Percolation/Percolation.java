/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       4/17/2017
 *  Last updated:  4/18/2017
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  
 *  Create n by n system and demonstrate percolation
 *
 *----------------------------------------------------------------*/


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int gridSize;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf; //source
    private WeightedQuickUnionUF uf2; // sink
    private int openSites = 0;

    /**
     * create n-by-n grid, with all sites set to false(blocked). connect the
     * source and sink to the top and bottom rows
     * @param N
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        this.gridSize = N;
        this.grid = new boolean[N + 1][N + 1];
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.uf2 = new WeightedQuickUnionUF(1 + N * N);
        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
            uf2.union(0, i);
            uf.union(N * N - i + 1, N * N + 1);

        }

    }

    /**
     * make sure arguments are valid open site (row, col) if it is not open
     * already links site to open neighbors
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validate(row, col);

        if (!this.grid[row][col]) {
            this.grid[row][col] = true;
            openSites++;

            //if above site open connect top
            if (row != 1 && isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                uf2.union(xyTo1D(row, col), xyTo1D(row - 1, col));

            }
            //connect bottom
            if (row != gridSize && isOpen(row + 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                uf2.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            //connect left
            if (col != 1 && isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                uf2.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            //connect right
            if (col != gridSize && isOpen(row, col + 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                uf2.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
    }

    /**
     * checks if site is open
     * @param row
     * @param col
     * @return 
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return this.grid[row][col];
    }

    /**
     * checks if site is full
     * @param row
     * @param col
     * @return 
     */
    public boolean isFull(int row, int col) {
        validate(row, col);

        if (!isOpen(row, col)) {
            return false;
        }
        return uf2.connected(0, xyTo1D(row, col));

    }

    /**
     * returns the number of open sites
     * @return 
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * confirms if system successfully percolates
     * @return 
     */
    public boolean percolates() {
        if (gridSize == 1) {
            return isOpen(1, 1);
        }
        return uf.connected(0, gridSize * gridSize + 1);
    }

    /**
     * maps 2D site to 1D site
     */
    private int xyTo1D(int row, int col) {
        return (row - 1) * gridSize + col;
    }

    /**
     * validates site, throws out of bounds if non-compliant
     */
    private void validate(int row, int col) {
        if (row < 1 || row > gridSize) {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }
        if (col < 1 || col > gridSize) {
            throw new IndexOutOfBoundsException("col index out of bounds");
        }
    }

    /*public static void main(String[] args) {
        Percolation perky = new Percolation(3);

        perky.open(1, 2);
        perky.open(2, 2);
        perky.open(2, 3);
        perky.open(3, 1);
        System.out.println(perky.percolates());
        perky.open(3, 3);
        System.out.println(perky.percolates());
        System.out.println(perky.isFull(2, 2));

    }*/
}
