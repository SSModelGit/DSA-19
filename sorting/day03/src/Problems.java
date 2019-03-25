import java.util.Arrays;
import java.lang.Math;
import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        if (A.length <= 10) {
            InsertionSort is = new InsertionSort();
            is.sort(A);
        }
        else {
            int dCount = 0;
            for (int i = 0; i < A.length; i++) {
                if (i != 0) {
                    dCount += A[i] - A[i-1];
                }
            }
            if (dCount > A.length * (int) Math.log((double) A.length) - A.length) {
                QuickSort qs = new QuickSort();
                A = qs.sort(A);
            }
            else {
                CountingSort cs = new CountingSort();
                cs.countingSort(A);
            }
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        int b = 26;
        LinkedList<String>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();
        for (String j : A) {
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            L[getNthCharacter(j, n)].add(j);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for (String jp : list) {
                A[j] = jp;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int l = 0; l < stringLength; l++) {
            countingSortByCharacter(S, l);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }
}
