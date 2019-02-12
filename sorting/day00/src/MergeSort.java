import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * Best-case runtime: O(n log(n))
     * Worst-case runtime: O(n log(n))
     * Average-case runtime: O(n log(n))
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= INSERTION_THRESHOLD) {
            InsertionSort sorter = new InsertionSort();
            return sorter.sort(array);
        }
        else {
            int[] left = sort(Arrays.copyOfRange(array, 0, array.length/2));
            int[] right = sort(Arrays.copyOfRange(array, array.length/2, array.length));
            return merge(left, right);
        }
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] arr = new int[a.length + b.length];
        int i, j, k;
        i = j = k = 0;
        while (i != a.length && j != b.length) {
            if (a[i] < b[j]) {
                arr[k] = a[i];
                k++;
                i++;
            }
            else if (a[i] >= b[j]) {
                arr[k] = b[j];
                k++;
                j++;
            }
        }
        if (i == a.length && j != b.length) {
            while (j < b.length) {
                arr[k] = b[j];
                j++;
                k++;
            }
            return arr;
        }
        else if (j == b.length && i != a.length) {
            while (i < a.length) {
                arr[k] = a[i];
                i++;
                k++;
            }
            return arr;
        }
        return arr;
    }
}
