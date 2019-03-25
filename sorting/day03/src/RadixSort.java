import java.util.LinkedList;

public class RadixSort {

    /**
     * @param n the digit number, 0 is least significant
     * @return
     */
    private static int getNthDigit(int number, int base, int n) {
        return number / ((int) Math.pow(base, n)) % base;
    }


    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByDigit(int[] A, int b, int n) {
        LinkedList<Integer>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();
        for (int i : A) {
            L[getNthDigit(i, b, n)].add(i);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            for (int jp : list) {
                A[j] = jp;
                j++;
            }
        }
    }

    /**
     * Runtime: O(w(n+b)) =~ O(n)
     *
     * n: length of array
     * w: word length of integers A in base b (equal to log base b of k (log_b k) )
     *
     * @param b The base to use for radix sort
     */
    static void radixSort(int[] A, int b) {
        // Calculate the upper-bound for numbers in A
        int k = A[0] + 1;
        for (int i = 1; i < A.length; i++)
            k = (A[i] + 1 > k) ? A[i] + 1 : k;
        int w = (int) Math.ceil(Math.log(k) / Math.log(b)); // w = log base b of k, word length of numbers
        for (int l = 0; l < w; l++) {
            countingSortByDigit(A, b, l);
        }
    }

    static void printArray(int arr[]) 
    { 
        for (int i=0; i<arr.length; i++) 
            System.out.print(arr[i]+" ");  
    } 
  
    // Debugger program 
    /* public static void main(String args[]) 
    { 
        int arr[] = {5,6,8,3,1,10,2,5}; 

        radixSort(arr, 10);
        System.out.println("sorted array: ");
        printArray(arr); 
    } */
}
