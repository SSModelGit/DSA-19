import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        HashMap<Integer, Integer> m;

        int[] searchP;
        int diff, count, calcVal;
        diff = count = calcVal = 0;
        for (int i = 0; i < points.length; i++) {
            m = new HashMap<Integer, Integer>();
            searchP = points[i];
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    diff = diffBoom(searchP, points[j]);
                    System.out.println("Calculating diff." + diff);
                    if (m.containsKey(diff)) {
                        m.put(diff, m.get(diff)+1);
                    }
                    else {
                        m.put(diff, 1);
                    }
                }
            }
            for (HashMap.Entry<Integer, Integer> e : m.entrySet()) {
                calcVal = e.getValue() * (e.getValue()-1);
                System.out.println("Map Value: " + e.getValue() + "Calc Val: " + calcVal);
                count = count + calcVal;
                System.out.println("Count Value: " + count);
            }
        }
        return count;
    }

    public static int diffBoom(int[] start, int[] end) {
        int a = start[0] - end[0];
        int b = start[1] - end[1];
        return a*a + b*b;
    }

    /* public static void main(String[] args) 
    { 
        Boomerang boom = new Boomerang();
        int[][] points1 = new int[][]{{0, 0}, {0, 1}, {1, 1}}; // 2
        int[][] points2 = new int[][]{{0, 0}, {2, 0}, {0, 2}, {0, -2}, {-2, 0}}; // 20
        int[][] points3 = new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}}; // 8
        // 8
        int[][] pointsL = new int[][]{{35, 27}, {-37, -8}, {17, 49}, {-12, -1}, {20, -30}, {-11, -9}, {-5, 18}, {15, -49}, {-32, -42}, {47, -30}, {-43, 47}, {-25, -22}, {-15, -2}, {13, 30}, {36, 5}, {21, -50}, {26, 27}, {13, 11}, {49, -22}, {34, 37}, {40, 28}, {-9, 24}, {24, -48}, {-27, 23}, {29, 48}, {-9, 9}, {40, -41}, {18, -17}, {-29, 16}, {6, -35}};
        System.out.println(boom.numberOfBoomerangs(points3));
    } */
}