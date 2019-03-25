public class Problems {

    public static int leastSum(int[] A) {
        int side = 1;
        String num1, num2;
        int count[] = new int[10];

        num1 = num2 = "0";

        // store count of each character 
        for (int i=0; i<A.length; ++i) 
            ++count[A[i]];

        // Change count[i] so that count[i] now contains actual 
        // position of this character in output array 
        for (int i=1; i<10; ++i) {
            while (count[i] > 0) {
                if (side == 1) {
                    num1 += i;
                    side = 2;
                }
                else {
                    num2 += i;
                    side = 1;
                }
                count[i]--;
            }
        }
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        return n1 + n2;
    }
}
