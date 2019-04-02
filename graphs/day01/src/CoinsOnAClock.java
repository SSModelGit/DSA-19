import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        HashMap<Character, Integer> pnd = new HashMap<Character, Integer>();
        pnd.put('p', pennies);
        pnd.put('n', nickels);
        pnd.put('d', dimes);
        pnd.put('t', hoursInDay);
        pnd.put('a', 0);
        List<char[]> result = new ArrayList<>();
        coinsHelper(0, new char[hoursInDay], pnd, result);
        return result;
    }

    private static void coinsHelper(int pos, char[] current, HashMap<Character, Integer> info, List<char[]> res) {
        if (info.get('a') == info.get('t')) {
            res.add(copier(current));
        }
        else {
            if (current[pos] == '\0') {
                for (char i : "pnd".toCharArray()) {
                    if (info.get(i) > 0) {
                        current[pos] = i;
                        info.put(i, info.get(i) - 1);
                        info.put('a', info.get('a') + 1);
                        int npos = pos;
                        switch(i) {
                            case 'p':
                                npos = (pos + 1) % info.get('t');
                                break;
                            case 'n':
                                npos = (pos + 5) % info.get('t');
                                break;
                            case 'd':
                                npos = (pos + 10) % info.get('t');
                                break;
                        }
                        coinsHelper(npos, current, info, res);
                        current[pos] = '\0';
                        info.put(i, info.get(i) + 1);
                        info.put('a', info.get('a') - 1);
                    }
                }
            }
        }
    }

    private static char[] copier(char[] A) {
        char[] B = new char[A.length];
        System.arraycopy(A, 0, B, 0, A.length);
        return B;
    }

    public static void main(String args[]) {
        List<char[]> foo = coinsOnAClock(4, 4, 4, 12);
        System.out.println(foo.size());
        for (char[] i : foo) {
            cPrint(i);
        }
    }

    private static void cPrint(char[] A) {
        System.out.print("[");
        for (char i : A) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
}
