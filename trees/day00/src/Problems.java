import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (n1.hasLeftChild() && n1.hasRightChild() && n2.hasLeftChild() && n2.hasRightChild()) { 
            boolean direct = n1.leftChild.equals(n2.leftChild) && n1.rightChild.equals(n2.rightChild);
            boolean flipped = n1.leftChild.equals(n2.rightChild) && n1.rightChild.equals(n2.leftChild);
            if (!(direct || flipped)) {
                return false;
            }
            else if (direct) {
                return isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild);
            }
            else {
                return isIsomorphic(n1.leftChild, n2.rightChild) && isIsomorphic(n1.rightChild, n2.leftChild);
            }
        }
        // Null case exceptions
        else {
            boolean n1L = n1.hasLeftChild();
            boolean n1R = n1.hasRightChild();
            boolean n2L = n2.hasLeftChild();
            boolean n2R = n2.hasRightChild();
            if (!(n1L || n1R || n2L || n2R)) {
                return true;
            }
        }
        return false;
    }
}
