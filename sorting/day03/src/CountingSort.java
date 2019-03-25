public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n+k)
     *
     * k: range of array A = range = uBound - lBound
     */
    static void countingSort(int[] A) {
        if (A.length > 2) {
            int lBound = minArray(A);
            int uBound = maxArray(A);
            int[] temp = new int[A.length];
            System.arraycopy(A, 0, temp, 0, A.length);
            int range = uBound - lBound + 1;

            int[] count = new int[range];

            for (int i = 0; i < A.length; i++) {
                count[A[i] - lBound]++;
            }

            for (int j = 1; j < range; j++) {
                count[j] += count[j-1];
            }

            for (int k = 0; k < temp.length; k++) {
                A[count[temp[k]-lBound]-1] = temp[k];
                count[temp[k]-lBound]--;
            }
        } 
    }

    static int maxArray(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static int minArray(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    static void printArray(int arr[]) 
    { 
        for (int i=0; i<arr.length; i++) 
            System.out.print(arr[i]+" ");  
    } 
  
    // Debugger program 
    /* public static void main(String args[]) 
    { 
        int arr[] = {5,6,8,3,-1,10,2,5}; 

        countingSort(arr);
        System.out.println();
        System.out.println("sorted array: ");
        printArray(arr); 
    } */
}
