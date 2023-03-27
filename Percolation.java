import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    static int row, column;
    public static int size=200;
    private static int bottom;
    private final boolean[][] opened;
    public static int openSites;
    private static WeightedQuickUnionUF qu;


    public Percolation(){
        qu = new WeightedQuickUnionUF(size*size +2);
        opened = new boolean[size][size];
        bottom = size*size + 1;
        openSites = 0;
    }

    public void  open() {
            row = StdRandom.uniformInt(1, size + 1);
            column = StdRandom.uniformInt(1, size + 1);
            if (!isOpen(row, column)) {
                opened[row - 1][column - 1] = true;
                openSites++;
            }
    }
    public void  union() {
            if (row == 1) {
                qu.union(findIndex(row, column), TOP);
            }
            if (row == size) {
                qu.union(findIndex(row, column), bottom);}
            if (row > 1 && isOpen(row - 1, column)) {
                qu.union(findIndex(row, column), findIndex(row - 1, column));
            }
            if (row < size && isOpen(row + 1, column)) {
                qu.union(findIndex(row, column), findIndex(row + 1, column));
            }
            if (column > 1 && isOpen(row, column - 1)) {
                qu.union(findIndex(row, column), findIndex(row, column - 1));
            }
            if (column < size && isOpen(row, column + 1)) {
                qu.union(findIndex(row, column), findIndex(row, column + 1));
            }
        }

    public int findIndex(int row, int column){
        return size *(row-1) + column;
    }
    public static boolean percolates(){
        return qu.connected(TOP, bottom );
    }
    public boolean isOpen(int row, int column) {

        return opened[row-1][column-1];
    }
}
