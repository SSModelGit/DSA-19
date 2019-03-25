package range_finding;

import java.awt.font.NumericShaper.Range;
import java.util.LinkedList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.desc--;
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.desc = getDesc(n);
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // runtime = O(log(n) + hi-lo+1)
    public List<Integer> rangeIndex(int lo, int hi) {
        List<Integer> l = new LinkedList<>();
        iot(root, l, lo, hi);
        return l;
    }

    private void iot(RangeNode<Integer> node, List<Integer> list, int lower, int upper) {
        if (node != null) {
            System.out.println("Node val: " + node.key);
            boolean leftConditional = false;
            leftConditional = !(node.key < lower && node.hasLeftChild() && node.leftChild.key < lower);
            if (leftConditional)
                iot(node.leftChild, list, lower, upper);
            if (node.key <= upper && node.key >= lower)
                list.add(node.key);
                System.out.println("Key val: " + node.key);
            if (node.key <= upper)
                iot(node.rightChild, list, lower, upper);
        }
    }

    // return the number of keys between [lo, hi], inclusive
    // runtime = O(log(n))
    public int rangeCount(int lo, int hi) {
        return rank(root, hi+1) - rank(root, lo);
    }

    public int rank(RangeNode<Integer> n, int k) {
        int rank = 0;
        if (n != null) {
            if (n.key >= k) {
                return rank(n.leftChild, k);
            }
            else {
                if (n.leftChild != null) {
                    rank += n.leftChild.desc + 2;
                }
                else {
                    System.out.println();
                    rank++;
                }
                return rank + rank(n.rightChild, k);
            }
        }
        return 0;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        RangeNode<Integer> right = y.rightChild;
        x.leftChild = right;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));

        x.desc = getDesc(x);
        y.desc = getDesc(y);
        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        RangeNode<Integer> left = y.leftChild;
        x.rightChild = left;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));

        x.desc = getDesc(x);
        y.desc = getDesc(y);
        return y;
    }

    private int getDesc (RangeNode<Integer> n) {
        if (n == null) {
            return 0;
        }
        int ld = (n.leftChild != null) ? n.leftChild.desc + 1: 0;
        int rd = (n.rightChild != null) ? n.rightChild.desc + 1: 0;
        return ld + rd;
    }

    public static void main(String args[]) 
    {
        Integer[][] inputs;
        AVLRangeTree[] rangeTree;
        rangeTree = new AVLRangeTree[6];
        for (int i = 0; i < rangeTree.length; i++) {
            rangeTree[i] = new AVLRangeTree();
        }
        inputs = new Integer[6][];
        inputs[0] = new Integer[]{3, 2, 1, 4};
        inputs[1] = new Integer[]{3, 1, 2, 7, 10, -3, 5, -10, 16, 13};
        inputs[2] = new Integer[]{-4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        inputs[3] = new Integer[]{13, 12, 10, 5, 3, -1, -7, -10, -50};
        inputs[4] = new Integer[]{23, 15, 10, 8, 40, 38, 37, 36, 24, 25, 26, 27};
        inputs[5] = new Integer[]{2, 17, 45, 3, 90, 16, 71, 37, 61, 36, 24, 56, 48, 57, 30, 81, 73, 1, 18, 33, 90, 8, 69, 3, 90, 31, 92, 74, 11, 80, 30, 53, 38, 93, 57, 60, 88, 64, 26, 25, 0, 76, 15, 40, 2, 36, 44, 10, 84, 99, 13, 36, 91, 12, 92, 8, 72, 72, 2, 45, 72, 79, 92, 9, 35, 89, 18, 83, 59, 54, 21, 12, 61, 2, 60, 87, 72, 79, 64, 83, 67, 76, 8, 57, 32, 91, 19, 48, 34, 81, 89, 98, 94, 23, 62, 93, 34, 16, 7, 10};
        for (int i = 0; i < 6; i++) {
            rangeTree[i].addAll(inputs[i]);
        }
        // System.out.println("Rank of -3::::: ");
        // System.out.println(rangeTree[1].rank(rangeTree[1].root, -3));
        // System.out.println("Rank of 16::::: ");
        // System.out.println(rangeTree[1].rank(rangeTree[1].root, 16));
        // System.out.println(rangeTree[1].rangeCount(-3, 16));
        System.out.println(rangeTree[1].rangeIndex(0, 8));
    }
}
