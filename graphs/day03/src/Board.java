import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[] zeropos = new int[]{-1, -1};
    public int[][] tiles;
    private HashMap<Integer, Integer[]> sol = new HashMap<>();

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        // Define what n is
        n = (b.length == b[0].length) ? b.length : 0;
        tiles = b;
        for (int p = 0; p < n; p++) {
            for (int m = 0; m < n; m++) {
                if (tiles[p][m] == 0) {
                    zeropos = new int[]{p, m};
                }
            }
        }
        // System.out.println("This is the zeroth position: " + zeropos[0] + ", " + zeropos[1]);
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sol.put(count, new Integer[] {i, j});
                count++;
            }
        }
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        Integer[] solVal = new Integer[] {0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                solVal = (tiles[i][j] != 0) ? sol.get(tiles[i][j]) : new Integer[]{i, j};
                dist += Math.abs(solVal[0] - i) + Math.abs(solVal[1] - j);
            }
        }
        return dist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        if (manhattan() > 0) {
            return false;
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        /* int count = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (tiles[i][j] > 0 && tiles[j][i] > 0 && tiles[j][i] > tiles[i][j])
                    count++;
            }
        }
        if (count%2 == 0)
            return true;
        return false; */
        int count = 0;
        int temp = 0;
        for (int i = 0; i < 8; i++) {
            temp = tiles[(int) i/3][i%3];
            if (temp != 0) {
                for (int j = i+1; j < 9; j++) {
                    if (tiles[(int) j/3][j%3] != 0 && tiles[(int) j/3][j%3] < temp) {
                        count++;
                    }
                }
            }
        }
        if (count % 2 == 0) {
            return true;
        }
        return false;
    }

    private static int[][] copyOf(int[][] A) {
        int[][] B = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors(boolean fastQ) {
        // TODO: Your code here
        ArrayList<Board> boo = new ArrayList<>();
        if (fastQ) {
            fastFindNeighbors(zeropos, 0, copyOf(tiles), boo);
        }
        else {
            slowFindNeighbors(zeropos, 0, copyOf(tiles), boo);
        }
        Iterable<Board> foo = boo;
        return foo;
    }

    private void slowFindNeighbors(int[] pos, int dir, int[][] bt, ArrayList<Board> board) {
        int temp = 0;
        switch(dir) {
            case 0: // go up
                if (pos[0] != 0) {
                    temp = bt[pos[0]-1][pos[1]];
                    bt[pos[0]-1][pos[1]] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                }
                break;
            case 1: // go down
                if (pos[0] != n-1) {
                    temp = bt[pos[0]+1][pos[1]];
                    bt[pos[0]+1][pos[1]] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                }
                break;
            case 2: // go right
                if (pos[1] != n-1) {
                    temp = bt[pos[0]][pos[1]+1];
                    bt[pos[0]][pos[1]+1] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                }
                break;
            case 3: // go left
                if (pos[1] != 0) {
                    temp = bt[pos[0]][pos[1]-1];
                    bt[pos[0]][pos[1]-1] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                }
                break;
            default:
                break;
        }
        switch(dir) {
            case 3:
                break;
            default:
                slowFindNeighbors(zeropos, dir + 1, copyOf(tiles), board);
                break;
        }
    }

    private void fastFindNeighbors(int[] pos, int dir, int[][] bt, ArrayList<Board> board) {
        int temp = 0;
        switch(dir) {
            case 0: // go up
                if (pos[0] != 0) {
                    temp = bt[pos[0]-1][pos[1]];
                    bt[pos[0]-1][pos[1]] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                    fastFindNeighbors(new int[]{pos[0]-1,pos[1]}, dir, bt, board);
                }
                break;
            case 1: // go down
                if (pos[0] != n-1) {
                    temp = bt[pos[0]+1][pos[1]];
                    bt[pos[0]+1][pos[1]] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                    fastFindNeighbors(new int[]{pos[0]+1,pos[1]}, dir, bt, board);
                }
                break;
            case 2: // go right
                if (pos[1] != n-1) {
                    temp = bt[pos[0]][pos[1]+1];
                    bt[pos[0]][pos[1]+1] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                    fastFindNeighbors(new int[]{pos[0],pos[1]+1}, dir, bt, board);
                }
                break;
            case 3: // go left
                if (pos[1] != 0) {
                    temp = bt[pos[0]][pos[1]-1];
                    bt[pos[0]][pos[1]-1] = bt[pos[0]][pos[1]];
                    bt[pos[0]][pos[1]] = temp;
                    board.add(new Board(copyOf(bt)));
                    fastFindNeighbors(new int[]{pos[0],pos[1]-1}, dir, bt, board);
                }
                break;
            default:
                break;
        }
        if (pos[0] == zeropos[0] && pos[1] == zeropos[1]) {
            switch(dir) {
                case 3:
                    break;
                default:
                    fastFindNeighbors(zeropos, dir + 1, copyOf(tiles), board);
                    break;
            }
        }
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 5}, {4, 3, 8}, {7, 6, 0}};

        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("-----\nNeighbors:");
        Iterable<Board> it = board.neighbors(true);
        int c = 1;
        for (Board i : it) {
            System.out.println("Board " + c);
            System.out.println(Arrays.deepToString(i.tiles));
            c++;
        }
    }
}
