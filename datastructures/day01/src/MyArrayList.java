public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
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
        checkIndex(index,"get");
        return elems[index];
    }

    // Runtime: O(n)
    public Cow remove(int index) {
        checkIndex(index,"remove");
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
        checkIndex(index,"add");
        
        for (int i = index; i<size-1; i++) {
            elems[i+1] = elems[i];
        }
        elems[index] = c;

        size++;
        resize(elems);
    }

    // Runtime: O(n)
    public void resize(Cow[] c) {
        Cow[] newElems;
        if (c.length / 4 > size) {
            if (c.length/2 > 1) {
                newElems = new Cow[c.length/2];
            }
            else {
                newElems = new Cow[2];
            }
            System.arraycopy(c, 0, newElems, 0, size);
            elems = newElems;
        }
        if (c.length == size) {
            newElems = new Cow[c.length * 2];
            System.arraycopy(c, 0, newElems, 0, size);
            elems = newElems;
        }
    }
    // Runtime: O(1)
    public void checkIndex(int idx, String testType) {
        if(idx < 0 || idx > size) 
        {
            throw new IndexOutOfBoundsException();
        }
        if((testType == "remove" || testType == "get") && idx == size) {
            throw new IndexOutOfBoundsException();
        }
    }
}