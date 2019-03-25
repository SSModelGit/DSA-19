import java.util.*;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        return findOneDPeak(nums, 0, nums.length-1);
    }

    public static int findOneDPeak(int[] nums, int lo, int hi) {
        // System.out.println("Lo: " + lo + " | Hi: " + hi);
        if (hi - lo < 0) {
            return 0;
        }
        if (hi == lo) {
            return hi;
        }
        int m = (hi - lo)/2 + lo;
        int j = peakOneD(m, nums);
        // System.out.println("m: " + m + " | nums[m]: " + nums[m]+ " | j: " + j);
        // System.out.println("------");
        switch(j) {
            case 0:
                return m;
            case -1:
                return findOneDPeak(nums, lo, m-1);
            case 1:
                return findOneDPeak(nums, m+1, hi);
            default:
                return 0;
        }
    }

    public static int[] findTwoDPeak(int[][] nums) {
        return findTwoDPeak(nums, new int[]{0,nums[0].length-1}, new int[]{0,nums.length-1});
    }

    public static int[] findTwoDPeak(int[][] nums, int[] xlim, int[] ylim) {
        System.out.print("X limits: ");
        printArray(xlim);
        System.out.print("| Y limits: ");
        printArray(ylim);
        System.out.println();
        if (xlim[1] - xlim[0] < 0 || ylim[1] - ylim[0] < 0) {
            return new int[]{0,0};
        }
        if (xlim[1] - xlim[0] == 0 && ylim[1] - ylim[0] == 0) {
            return new int[]{ylim[0], xlim[0]};
        }
        if (xlim[1] - xlim[0] != 0 && ylim[1] - ylim[0] == 0) {
            int x = findOneDPeak(nums[ylim[0]]);
            return new int[]{ylim[0], x};
        }
        // X-middle and Y-middle
        int[] m = new int[]{(xlim[1] - xlim[0]) / 2 + xlim[0], (ylim[1] - ylim[0]) / 2 + ylim[0]};
        // peakX and peakY automatically handle X and Y coordinate switching
        int[] j = new int[]{peakX(m[0], m[1], nums), peakY(m[0], m[1], nums)};
        System.out.print("m: ");
        printArray(m);
        System.out.print("| nums[m]: " + nums[m[1]][m[0]] + " | j: ");
        printArray(j);
        System.out.println();
        if (j[0] == j[1] && j[0] == 0) {
            return new int[]{m[1], m[0]};
        }
        int[] xnew = new int[]{xlim[0], xlim[1]};
        int[] ynew = new int[]{ylim[0], ylim[1]};
        System.out.print("xnew: ");
        printArray(xnew);
        System.out.print("| ynew: ");
        printArray(ynew);
        System.out.println();
        // shift X limits
        if (j[0] == 0) {
            xnew = new int[]{m[0], m[0]};
        }
        else if (j[0] == 1) {
            xnew[0] = m[0] + 1;
        }
        else {
            xnew[1] = m[0] - 1;
        }
        // shift Y limits
        if (j[1] == 0) {
            ynew = new int[]{m[1], m[1]};
        }
        else if (j[1] == 1) {
            ynew[0] = m[1] + 1;
        }
        else {
            ynew[1] = m[1] - 1;
        }
        System.out.print("xnew: ");
        printArray(xnew);
        System.out.print("| ynew: ");
        printArray(ynew);
        System.out.println("\n-------");
        return findTwoDPeak(nums, xnew, ynew);
    }

    static void printArray(int arr[]) 
    { 
        for (int i=0; i<arr.length; i++) 
            System.out.print(arr[i]+" ");  
    }

    public static void print2D(int mat[][]) 
    { 
        // Loop through all rows 
        for (int[] row : mat) 
  
            // converting each row as string 
            // and then printing in a separate line 
            System.out.println(Arrays.toString(row)); 
    }
  
    // Debugger program 
    public static void main(String args[]) 
    { 
        Random gen = new Random(1);
        int[][] nums = new int[90][110];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                nums[i][j] = gen.nextInt() % 1000;
            }
        }

        System.out.println("Array: ");
        print2D(nums);
        System.out.println();

        int[] p = findTwoDPeak(nums);
        System.out.println();
        System.out.print("Peak Position: ");
        printArray(p);
        System.out.println("| Peak value: " + nums[p[0]][p[1]]);
        // printArray(arr); 
    }

}