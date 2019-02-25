import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys

        HashMap<Character, Integer> map = new HashMap<>();

        // Initialize HashMap
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], i);
        }

        sort(locks, map, keys);

        for (int i = 0; i < locks.length; i++) {
            if (map.containsKey(locks[i])) {
                keys[i] = locks[i];
            }
        }

        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        return result;
    }

    private static void shuffleArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    public static char[] sort(char[] array, HashMap<Character, Integer> m, char[] sArray) {
        shuffleArray(array);
        quickSort(array, 0, array.length-1, m, sArray);
        return array;
    }

    public static void quickSort(char[] a, int lo, int hi, HashMap<Character, Integer> m, char[] sArray) {
        if (lo < hi) {
            char pivot = sArray[m.get(a[lo])];
            int p = partition(a, lo, hi, pivot);

            quickSort(a, lo, p-1, m, sArray);
            quickSort(a, p+1, hi, m, sArray);
        }
    }

    public static int partition(char[] array, int lo, int hi, char pivot) {
        int i = lo; // index of smaller element 
        for (int j=lo+1; j<=hi; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (array[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                swap(array, i, j);
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot)
        swap(array, i, lo);
        return i;
    }
}




