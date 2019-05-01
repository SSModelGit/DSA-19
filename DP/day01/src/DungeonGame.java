import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        HashMap<Integer, Integer> worstStates = new HashMap<Integer, Integer>();
        int hSize = map.length+1;
        // Initialize HashMap of worst states with padded ends
        for (int i = 0; i < hSize; i++) {
            worstStates.put(mP(i, 0, hSize), -1000);
        }
        for (int i = 0; i < hSize; i++) {
            worstStates.put(mP(0, i, hSize), -1000);
        }

        // Initialize cost matrix with padded ends
        int[][] hMap = new int[hSize][hSize];
        for (int i = 0; i < hSize; i++) {
            hMap[i][0] = -1000;
        }
        for (int i = 0; i < hSize; i++) {
            hMap[0][i] = -1000;
        }

        // Begin bottom-up process
        for (int j = 1; j < hMap.length; j++) {
            for (int i = 1; i < hMap.length; i++) {
                if (i == 1 && i == j) {
                    hMap[i][j] = map[i-1][j-1];
                    worstStates.put(mP(i, j, hSize), hMap[i][j]);
                }
                else {
                    int upW, leftW, upN, leftN;
                    upN = map[i-1][j-1] + hMap[i-1][j];
                    leftN = map[i-1][j-1] + hMap[i][j-1];
                    upW = Math.min(worstStates.get(mP(i-1, j, hSize)), upN);
                    leftW = Math.min(worstStates.get(mP(i, j-1, hSize)), leftN);
                    if (upW == leftW) {
                        hMap[i][j] = Math.max(upN, leftN);
                        worstStates.put(mP(i, j, hSize), upW);
                    }
                    else if (upW < leftW) {
                        hMap[i][j] = leftN;
                        worstStates.put(mP(i, j, hSize), leftW);
                    }
                    else {
                        hMap[i][j] = upN;
                        worstStates.put(mP(i, j, hSize), upW);
                    }
                }
            }
        }
        printBoard(hMap);
        return -1 * hMap[hMap.length-1][hMap[0].length-1] + 1;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public static int mP(int i, int j, int s) {
        return i * s + j;
    }

    public static void main(String[] args) {
        int[][] map = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        // int[][] map = {{-2,-3,-4,-1,-3},{3,0,-4,0,0},{0,1,-4,-1,0},{-5,4,1,-4,-1},{2,-5,-3,-5,-4}};
        // int[][] map = {{-8,-15,-9,5,-6},{-10,-13,-4,-1,-4},{-14,4,-5,-9,-3},{-7,-10,-7,-12,-3},{-2,1,-3,5,-9}};
        System.out.println(minInitialHealth(map));
    }
}