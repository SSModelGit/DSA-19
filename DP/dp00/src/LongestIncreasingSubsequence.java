public class LongestIncreasingSubsequence {

    // Runtime: n^2
    // Space: n
 
    public static int LIS(int [] A){

        if (A.length == 0) {
            return 0;
        }
 
        int [] lis = new int[A.length];
        lis[0] = 1;
        int maximo = lis[0];
 
        for(int i=1; i<A.length; i++){
            lis[i] = 1 + maximumLIS(A,i,lis);
            maximo = Math.max(maximo, lis[i]);
        }
 
        return maximo;
    }

    private static int maximumLIS(int a[], int end, int [] lis){
        for (int i=0; i<end; i++){
            if( a[i] < a[end] && lis[i] > lis[end] )
                lis[end] = lis[i];
        }
        return lis[end];
    }

    public static void main(String[] args) {
        int[] A = {5, 3, 1, 7, 3, 9, 2, 1, 8, 30, 2, 18, 19, 13, 20, 7, 10, 16};
        System.out.println("Longest increasing sybsequence : " + LIS(A));
    }
}