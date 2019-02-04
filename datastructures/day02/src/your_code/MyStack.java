package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> lMax;

    public MyStack() {
        ll = new LinkedList<>();
        lMax = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (lMax.isEmpty()) {
            lMax.addFirst(e);
        }
        else {
            if (lMax.getFirst() <= e) {
                lMax.addFirst(e);
            }
        }
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == lMax.getFirst()) {
            lMax.removeFirst();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        if (ll.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return lMax.getFirst();
    }
}
