
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/*----------------------------------------------------------------
 *  Author:        Larry Osakwe
 *  Written:       5/4/2017
 *  Last updated:  5/5/2017
 *
 *  Compilation:   javac Board.java
 *  Execution:     java Board
 *  
 *  
 *  
 *----------------------------------------------------------------*/
public class Board {

    private final int[][] board;
    private final int n;

    /**
     * construct a board from an n-by-n array of blocks (where blocks[i][j] =
     * block in row i, column j)
     *
     * @param blocks
     */
    public Board(int[][] blocks) {
        this.n = blocks.length;
        this.board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.board[i][j] = blocks[i][j];
            }
        }

    }

    /**
     * board dimension n
     *
     * @return n
     */
    public int dimension() {
        return n;
    }

    /**
     * number of blocks out of place
     *
     * @return hamming count
     */
    public int hamming() {
        int count = 0;

        for (int i = 0; i < n * n; i++) {
            int r = i / n;
            int c = i % n;

            if (this.board[r][c] != 0 && this.board[r][c] != (i + 1)) {
                count++;
            }
        }
        return count;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     *
     * @return total Manhattan distances
     */
    public int manhattan() {
        int tot = 0;

        for (int i = 0; i < n * n; i++) {
            int r = i / n;
            int c = i % n;
            if (this.board[r][c] == 0) {
                continue;
            }
            int nr = (this.board[r][c] - 1) / n;
            int nc = (this.board[r][c] - 1) % n;
            tot += Math.abs(nr - r) + Math.abs(nc - c);

        }
        return tot;
    }

    /**
     * is this board the goal board?
     *
     * @return true if goal is met
     */
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     * 
     * @return 
     */
    public Board twin() {
        int[][] twin = new int[n][n];
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                twin[i][j] = board[i][j];
                if (twin[i][j] != 0 && twin[i][j + 1] != 0) {
                    swap(twin, i, j, i, j + 1);
                    flag = true;
                    break;
                }
            }
            if(flag) break;

        }

        return new Board(twin);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }


    @Override        
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (this.n != other.n) {
            return false;
        }
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }

    //public Iterable<Board> neighbors() {
    //}
    
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private void swap(int[][] hold, int i1, int j1, int i2, int j2) {
        int temp = hold[i1][j1];
        hold[i1][j1] = hold[i2][j2];
        hold[i2][j2] = temp;
    }

    public static void main(String[] args) {
        int[][] plug = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board test = new Board(plug);
        StdOut.println(test);
        StdOut.println(test.hamming());
        StdOut.println(test.manhattan());

    }

}
