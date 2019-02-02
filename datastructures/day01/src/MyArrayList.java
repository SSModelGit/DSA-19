public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        elems = Cow[10];
        size = 0;
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = Cow[capacity];
        size = 0;
    }

    // Runtime: O(1)*
    public void add(Cow c) {
        elems[size] = c;
        size++;
        resize(elems);
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        checkIndex(index);
        return elems[index];
    }

    // Runtime: O(n)
    public Cow remove(int index) {
        checkIndex(index);
        Cow removed = elems[index];

        for (int i=index; i<size-1; i++) {
            elems[i] = elems[i+1];
        }
        
        size--;
        resize(elems);
        return removed;
    }

    // Runtime: O(n)
    public void add(int index, Cow c) {
        checkIndex(index);
        
        for (int i = index; i<size-1; i++) {
            elems[i+1] = elems[i];
        }

        size++;
        resize(elems);
    }

    // Runtime: O(n)
    public void resize(Cow[] c) {
        
        if (c.length / 4 > size) {
            if (c.length/2 > 1) {
                Cow[] newElems = Cow[c.length/2];
            }
            else {
                Cow[] newElems = Cow[2];
            }
            System.arraycopy(c, 0, newElems, 0, size);
            elems = newElems;
        }
        if (c.length == size) {
            Cow[] newElems = Cow[c.length * 2];
            System.arraycopy(c, 0, newElems, 0, size);
            elems = newElems;
        }
    }
    // Runtime: O(1)
    public void checkIndex(int idx) {
        if(index < 0 || index > size) 
        {
            throw new IndexOutOfBoundsException();
        }
    }
}