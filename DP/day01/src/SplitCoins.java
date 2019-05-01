import java.util.Arrays;

public class SplitCoins {

    private static int closer(int a, int b, int t) {
        // a is option 1, b is option 2, t is the total
        double mid = t / 2.0;
        // System.out.println("a: " + a + " | b: " + b);
        if (Math.abs(a - mid) < Math.abs(b - mid)) return a;
        else return b;
    }

    private static int splitter(int[] coins, int sum, int i, int j, int[][] cT) {
        if (i == coins.length) return j;
        if (cT[i][j] != -1) return cT[i][j];
        cT[i][j] = closer(splitter(coins, sum, i+1, j, cT), splitter(coins, sum, i+1, j+coins[i], cT), sum);
        return cT[i][j];
    }
    
    public static int splitCoins(int[] coins) {
        int sum = Arrays.stream(coins).sum();
        int[][] cT = new int[coins.length][sum+1];
        for (int i = 0; i < cT.length; i++)
            for (int j = 0; j < cT[0].length; j++)
                cT[i][j] = -1;
        return Math.abs(sum - 2*splitter(coins, sum, 0, 0, cT));
    }

    public static void main(String[] args) {
        int[] c = new int[]{3, 1, 1, 2, 5, 7};
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.stream(c).sum());
        System.out.println(splitCoins(c));
    }
}
