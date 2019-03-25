public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int lC, rC;
        lC = leftChild(i);
        rC = rightChild(i);
        if (lC < size) {
            if (heap[i] < heap[lC]) {
                if (rC < size) {
                    if (heap[lC] <= heap[rC]) {
                        swap(heap, i, rC);
                        sink(rC);
                    }
                    else {
                        swap(heap, i, lC);
                        sink(lC);
                    }
                }
                else {
                    swap(heap, i, lC);
                    sink(lC);
                }
            }
            else if (rC < size) {
                if (heap[i] < heap[rC]) {
                    swap(heap, i, rC);
                    sink(rC);
                }
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: ""
     * Average-case runtime: ""
     *
     * Space-complexity: O(logn)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            sink(0);
            swap(heap, 0, i);
            size--;
        }

        return heap;
    }

    void printArray(int arr[], boolean cutOff) 
    { 
        int n = arr.length; 
        if (cutOff) {
            for (int i=0; i<size; ++i) 
                System.out.print(arr[i]+" "); 
        }
        else {
            for (int i=0; i<n; ++i) 
                System.out.print(arr[i]+" "); 
        }
    } 
  
    // Debugger program 
    /* public static void main(String args[]) 
    { 
        int arr[] = {1, 3, 2, 5, 1, 2, 7, 9};
  
        HeapSort ob = new HeapSort(); 
        arr = ob.sort(arr);

        System.out.println("sorted array: ");
        ob.printArray(arr, false); 
    } */
}
