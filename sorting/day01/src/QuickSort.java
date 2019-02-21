import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    // private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn), although quicksort itself has a worst-case of O(n^2)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(logn)
     */
    @Override
    public int[] sort(int[] array) {
        shuffleArray(array);
        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);

            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        int pivot = array[lo];
        int i = lo; // index of smaller element 
        for (int j=lo+1; j<=hi; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (array[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = array[i]; 
        array[i] = array[lo]; 
        array[lo] = temp;
        return i;
    }

    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" ");  
    }

    static void printArray(int arr[], int lo, int hi) 
    { 
        for (int i=lo; i<=hi; ++i) 
            System.out.print(arr[i]+" ");  
    } 
  
    // Debugger program 
    /* public static void main(String args[]) 
    { 
        int arr[] = {5,6,8,3,1,10,2,5}; 
  
        QuickSort ob = new QuickSort(); 
        arr = ob.sort(arr); 
        System.out.println("sorted array: ");
        printArray(arr); 
    } */
}
