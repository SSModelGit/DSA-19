import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        if (occupied.length == 0)
            return 0;
        if (occupied.length == 1)
            return 1;
        int numBoards = occupied.length;
        PriorityQueue<Integer> spaces = new PriorityQueue<Integer>();
        for (int i = 1; i < occupied.length; i++) {
                spaces.add(occupied[i] - occupied[i-1] - 1);
        }
        int numGaps = spaces.size() + 1;
        while (numGaps - M > 0) {
            numBoards += spaces.poll();
            numGaps--;
        }
        return numBoards;
    }

    /* public static void main(String[] args) {
        int boards = BarnRepair.solve(2, new int[]{1,2,4,5,6,12});
        System.out.println(boards);
    } */
}