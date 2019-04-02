import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<Integer> unused = new LinkedList<>();
        for(Integer i: A) {
            unused.add(i);
        }
        List<List<Integer>> perms = new LinkedList<>();
        permutationsHelper(new LinkedList<Integer>(), unused, perms);
        return perms;
    }

    public static void permutationsHelper(List<Integer> c, List<Integer> u, List<List<Integer>> p) {
        if (u.size() == 0) {
            p.add(Arrays.asList(c.toArray(new Integer[c.size()])));
        }
        else {
            for (Integer j : u.toArray(new Integer[u.size()])) {
                c.add(j);
                u.remove(j);
                permutationsHelper(c, u, p);
                c.remove(j);
                u.add(j);
            }
        }
    }

    private static void listPrinter(List<Integer> input) {
        System.out.print("[");
        for (Integer i : input) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }

    private static void listPrinter(List<Integer> input, int newline) {
        System.out.print("[");
        for (Integer i : input) {
            System.out.print(i + " ");
        }
        System.out.print("] ");
    }

    public static void main(String args[]) {
        List<List<Integer>> perm = permutations(Arrays.asList(1, 2, 3));
        for (List<Integer> i : perm) {
            listPrinter(i, 0);
        }
    }
}
