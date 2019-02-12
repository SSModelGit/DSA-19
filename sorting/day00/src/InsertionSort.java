public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) { 
        int n = array.length;
		for (int i=1; i<n; ++i) 
		{
			int p = i-1;
			while (p>=0 && array[p] > array[p+1]) 
			{ 
				swap(array, p, p+1);
                p--;
            }
        }
        return array;
    }
}
