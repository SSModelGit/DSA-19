package answers;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null) return -1;
        return n.height;
        // return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        int b = balanceFactor(n);
        TreeNode<T> head = null;
        if (b < -1) {
            if (balanceFactor(n.leftChild) > 0) {
                n.leftChild = rotateLeft(n.leftChild);
                n.leftChild.leftChild.height = 
                Math.max(height(n.leftChild.leftChild.leftChild), height(n.leftChild.leftChild.rightChild)) + 1;
            }
            head = rotateRight(n);
            root = (root.equals(n)) ? head : root;
            head.rightChild.height = Math.max(height(head.rightChild.leftChild), height(head.rightChild.rightChild)) + 1;
            head.height = Math.max(height(head.leftChild), height(head.rightChild)) + 1;
            return head;
        }
        else if (b > 1) {
            if (balanceFactor(n.rightChild) < 0) {
                n.rightChild = rotateRight(n.rightChild);
                n.rightChild.rightChild.height =
                Math.max(height(n.rightChild.rightChild.leftChild), height(n.rightChild.rightChild.rightChild)) + 1;
            }
            head = rotateLeft(n);
            root = (root.equals(n)) ? head : root;
            head.leftChild.height = Math.max(height(head.leftChild.leftChild), height(head.leftChild.rightChild)) + 1;
            head.height = Math.max(height(head.leftChild), height(head.rightChild)) + 1;
            return head;
        }
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> left = n.leftChild;
        TreeNode<T> foo = left.rightChild;
        left.rightChild = n;
        n.leftChild = foo;
        return left;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> right = n.rightChild;
        TreeNode<T> foo = right.leftChild;
        right.leftChild = n;
        n.rightChild = foo;
        return right;
    }

    // AND LET THE DEBUGGING BEGIN
    // Debugger program 
    public static void main(String args[]) 
    { 
        delTest();
    }

    static void printArray(Integer[] arr) 
    { 
        for (int i=0; i<arr.length; i++) 
            System.out.print(arr[i]+" ");  
    }

    static void delTest() {
        Integer[][] inputs;
        AVLTree<Integer>[] bsts;
        bsts = new AVLTree[5];
        for (int i = 0; i < bsts.length; i++) {
            bsts[i] = new AVLTree<>();
        }
        inputs = new Integer[5][];
        inputs[0] = new Integer[]{3, 2, 1, 4};
        inputs[1] = new Integer[]{3, 1, 2, 7, 10, -3, 5, -10, 16, 13};
        inputs[2] = new Integer[]{-4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        inputs[3] = new Integer[]{13, 12, 10, 5, 3, -1, -7, -10, -50};
        inputs[4] = new Integer[]{23, 15, 10, 8, 40, 38, 37, 36, 24, 25, 26, 27};
        for (int i = 0; i < 5; i++) {
            bsts[i].addAll(inputs[i]);
            assertValidAVL(bsts[i].root);
        }

        Integer[] input = inputs[2];
        AVLTree<Integer> bst = bsts[2];


        Integer[] sorted = sorted(input);
        List<Integer> list = Arrays.asList(sorted);
        list = new ArrayList<>(list);
        System.out.println("\nWhat are we dealing with?");
        System.out.println("Tree: " + bst.inOrderTraversal());
        System.out.print("Array: ");
        printArray(sorted);
        System.out.println("\n");
        for (int j = 0; j < input.length; j++) {
            int randomNum = ThreadLocalRandom.current().nextInt(input.length - j);
            System.out.println("Trying to remove this number " + list.get(randomNum));
            bst.delete(list.get(randomNum));
            list.remove(randomNum);
            Integer[] expected = Arrays.copyOf(list.toArray(), list.size(), Integer[].class);
            Object[] traversal = bst.inOrderTraversal().toArray();
            Integer[] received = Arrays.copyOf(traversal, traversal.length, Integer[].class);
            assertValidAVL(bst.root);
            System.out.println("\nReceived tree (sorted via iot traversal): " + bst.inOrderTraversal());
            printArray(expected);
            System.out.println("\n");
        }
    }

    static void aVLTest() {
        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < Math.pow(2, 3); i++) {
            bst.add(i);
            assertValidAVL(bst.root);
        }

        System.out.println("\n\n------\n------\nSwitch to deletion now.\n------\n------\n\n");

        for (int i = 0; i < Math.pow(2, 3); i += 1) {
            bst.delete(i);
            assertValidAVL(bst.root);
        }
        System.out.print("\nFinal tree (iot array display): ");
        System.out.println(bst.inOrderTraversal());
    }

    static int height(TreeNode node, int a) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.leftChild, -1), height(node.rightChild, -1));
    }

    static void assertValidAVL(TreeNode<Integer> node) {
        if (node == null) return;
        String bfG = (Math.abs(height(node.leftChild, -1) - height(node.rightChild, -1)) <= 1) ? "good" : "bad";
        String hG = (node.height == height(node, -1)) ? "good" : "bad";
        if (bfG.equals("bad")) {
            System.out.println("The balance factor for node " + node.key + " is " + bfG);
            System.out.print("\tNode's balance factor is ");
            System.out.println(Math.abs(height(node.leftChild, -1) - height(node.rightChild, -1)));
        }
        if (hG.equals("bad")) {
            System.out.println("The height matchup for node " + node.key + " is " + hG);
            System.out.println("\tStated height is " + node.height);
            System.out.println("\tProper height is " + height(node, -1));
        }
        if (hG.equals("bad") || bfG.equals("bad")) return; 
        assertValidAVL(node.leftChild);
        assertValidAVL(node.rightChild);
    }

    static Integer[] sorted(Integer[] A) {
        Integer[] sorted = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted);
        return sorted;
    }
}
