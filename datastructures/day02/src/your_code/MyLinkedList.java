package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0) {
            Node temp = new Node(c, null, null);
            head = temp;
            tail = temp;
            size++;
        }
        else {
            Node temp = new Node(c, tail, null);
            tail.next = temp;
            tail = temp;
            size++;
        }
    }

    public void addFirst(Chicken c) {
        if (size == 0) {
            Node temp = new Node(c, null, null);
            head = temp;
            tail = temp;
            size++;
        }
        else {
            Node temp = new Node(c, null, head);
            head.prev = temp;
            head = temp;
            size++;
        }
    }

    public Chicken get(int index) {
        checkSize(index);
        Node temp = head;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    public Chicken remove(int index) {
        checkSize(index);
        if (index == 0) {
            return removeFirst();
        }
        else if (index == size-1) {
            return removeLast();
        }
        else {
            Node temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            size--;
            return temp.val;
        }
    }

    public Chicken removeFirst() {
        checkSize(0);
        Chicken firstChicken = head.val;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head.next.prev = null;
            head = head.next;
        }
        size--;
        return firstChicken;
    }

    public Chicken removeLast() {
        checkSize(size-1);
        Chicken lastChicken = tail.val;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        size--;
        return lastChicken;
    }

    public void checkSize(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}