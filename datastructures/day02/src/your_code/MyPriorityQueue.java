package your_code;

import java.util.ArrayList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    
    private ArrayList<Integer> al = new ArrayList<Integer>();

    public void enqueue(int item) {
        if (al.size() == 0) {
            al.add(item);
        }
        else {
            boolean queued = false;
            int i = 0;
            while(!queued) {
                if (al.size() > i) {
                    if (item < al.get(i)) {
                        al.add(i, item);
                        queued = true;
                    }
                    else {
                        i++;
                    }
                }
                else {
                    al.add(item);
                    queued = true;
                }
            }
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        if (al.size() == 0) {
            return -1;
        }
        return al.remove(al.size()-1);
    }

}