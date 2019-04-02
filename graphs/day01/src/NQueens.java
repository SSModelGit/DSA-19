import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<char[][]> answers = new ArrayList<>();
        HashSet<Integer> numQ = new HashSet<Integer>(); // Stores columns occupied by Queens
        nQHelper(n, board, numQ, answers);
        return answers;
    }

    private static void nQHelper(int n, char[][] board, HashSet<Integer> qSet, List<char[][]> sol) {
        if (qSet.size() == n) {
            sol.add(copyOf(board));
            // System.out.println("Added board number " + sol.size());
        }
        else {
            int row = qSet.size();
            for (int i = 0; i < n; i++) {
                if (!qSet.contains(i)) {
                    board[row][i] = 'Q';
                    if (!checkDiagonal(board, row, i)) {
                        qSet.add(i);
                        nQHelper(n, board, qSet, sol);
                        qSet.remove(i);
                        board[row][i] = '.';
                    }
                    else {
                        board[row][i] = '.';
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        List<char[][]> sol = nQueensSolutions(15);
        System.out.println(sol.size());
        // printBoardList(sol);
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("[");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    private static void printBoardList(List<char[][]> boards) {
        for (int i = 0; i < boards.size(); i++) {
            System.out.println("-----");
            printBoard(boards.get(i));
        }
        System.out.println("-----");
    }
}
