import java.util.*;

public class Cryptarithmetic {

    // Do not modify this function (though feel free to use it)
    public static boolean validSolution(String S1, String S2, String S3, Map<Character, Integer> assignments) {
        return (stringToInt(S1, assignments) + stringToInt(S2, assignments) == stringToInt(S3, assignments))
                && assignments.get(S1.charAt(0)) != 0
                && assignments.get(S2.charAt(0)) != 0
                && assignments.get(S3.charAt(0)) != 0;
    }


    private static Iterable<Integer> randomOrder() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(l);
        return l;
    }

    private static int stringToInt(String s, Map<Character, Integer> assignments) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i *= 10;
            i += assignments.get(s.charAt(j));
        }
        return i;
    }

    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
        Map<Character, Integer> assignments = new HashMap<>();
        assignments.put('\0', 0);
        solver(0, 0, S1.toCharArray(), S2.toCharArray(), S3.toCharArray(), assignments);
        System.out.println("S1: " + S1 + " | " + stringToInt(S1, assignments));
        System.out.println("S2: " + S2 + " | " + stringToInt(S2, assignments));
        System.out.println("S3: " + S3 + " | " + stringToInt(S3, assignments));
        System.out.println("S1 + S2 = " + (stringToInt(S1, assignments) + stringToInt(S2, assignments)));
        return assignments;
    }

    private static boolean solver(int pos, int carry, char[] cs1, char[] cs2, char[] cs3, Map<Character, Integer> vals) {
        if (pos == cs3.length) {
            return true;
        }
        else if (pos == cs3.length - 1) {
            if (cs1.length < cs3.length && cs2.length < cs3.length) {
                if (!vals.containsKey(cs3[0]) || vals.get(cs3[0]) == 1) {
                    vals.put(cs3[0], 1);
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (cs1.length < cs3.length && cs2.length >= cs3.length) {
                if (!vals.containsKey(cs3[0]) || vals.get(cs3[0]) == vals.get(cs2[0])) {
                    vals.put(cs3[0], vals.get(cs2[0]));
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (cs2.length < cs3.length && cs1.length >= cs3.length) {
                if (!vals.containsKey(cs3[0]) || vals.get(cs3[0]) == vals.get(cs1[0])) {
                    vals.put(cs3[0], vals.get(cs1[0]));
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                if (!vals.containsKey(cs3[0]) || vals.get(cs3[0]) == vals.get(cs1[0]) + vals.get(cs2[0])) {
                    vals.put(cs3[0], vals.get(cs1[0]) + vals.get(cs2[0]));
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            System.out.println("At pos: " + pos);
            char c1 = (cs1.length > pos) ? cs1[cs1.length - 1 - pos] : '\0';
            char c2 = (cs2.length > pos) ? cs2[cs2.length - 1 - pos] : '\0';
            char c3 = (cs3.length > pos) ? cs3[cs3.length - 1 - pos] : '\0';
            System.out.println("Characters being used: " + c1 + " | " + c2 + " | " + c3);
            if (vals.containsKey(c1) && vals.containsKey(c2) && vals.containsKey(c3)) {
                if ((vals.get(c1) + vals.get(c2) + carry) % 10 == vals.get(c3)) {
                    carry = (Math.max(vals.get(c1) + vals.get(c2) + carry - 10, 0) >= 0) ? 1 : 0;
                    return solver(pos + 1, carry, cs1, cs2, cs3, vals);
                }
                else {
                    return false;
                }
            }
            else if (vals.containsKey(c1) && vals.containsKey(c2)) {
                vals.put(c3, ((vals.get(c1) + vals.get(c2) + carry) % 10));
                carry = (Math.max(vals.get(c1) + vals.get(c2) + carry - 10, 0) >= 0) ? 1 : 0;
                return solver(pos + 1, carry, cs1, cs2, cs3, vals);
            }
            else if (vals.containsKey(c1) && vals.containsKey(c3)) {
                vals.put(c2, (vals.get(c3) - vals.get(c1) - carry + 10) % 10);
                carry = (vals.get(c3) - vals.get(c1) - carry < 0) ? 1 : 0;
                return solver(pos + 1, carry, cs1, cs2, cs3, vals);
            }
            else if (vals.containsKey(c2) && vals.containsKey(c3)) {
                vals.put(c1, (vals.get(c3) - vals.get(c2) - carry + 10) % 10);
                carry = (vals.get(c3) - vals.get(c2) - carry < 0) ? 1 : 0;
                return solver(pos + 1, carry, cs1, cs2, cs3, vals);
            }
            else if (vals.containsKey(c1)) {
                int i = 0;
                vals.put(c2, i);
                vals.put(c3, (vals.get(c1) + i + carry) % 10);
                carry = (Math.max(vals.get(c1) + i + carry - 10, 0) >= 0) ? 1 : 0;
                while (!solver(pos + 1, carry, cs1, cs2, cs3, vals) && i < 10) {
                    i++;
                    vals.put(c2, i);
                    vals.put(c3, (vals.get(c1) + i + carry) % 10);
                    carry = (Math.max(vals.get(c1) + i + carry - 10, 0) >= 0) ? 1 : 0;
                }
                if (i < 10) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (vals.containsKey(c2)) {
                int i = 0;
                vals.put(c1, i);
                vals.put(c3, (vals.get(c2) + i + carry) % 10);
                carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                while (!solver(pos + 1, carry, cs1, cs2, cs3, vals) && i < 10) {
                    i++;
                    vals.put(c1, i);
                    vals.put(c3, (vals.get(c2) + i + carry) % 10);
                    carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                }
                if (i < 10) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (vals.containsKey(c3)) {
                int i = 0;
                vals.put(c1, i);
                vals.put(c2, (vals.get(c3) - i - carry + 10) % 10);
                carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                while (!solver(pos + 1, carry, cs1, cs2, cs3, vals) && i < 10) {
                    i++;
                    vals.put(c1, i);
                    vals.put(c2, (vals.get(c3) - i - carry + 10) % 10);
                    carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                }
                if (i < 10) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                int i = 0;
                int j = 0;
                vals.put(c1, i);
                vals.put(c2, j);
                vals.put(c3, (i + j + carry) % 10);
                carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                while (!solver(pos + 1, carry, cs1, cs2, cs3, vals) && i < 10) {
                    while (!solver(pos + 1, carry, cs1, cs2, cs3, vals) && j < 10) {
                        j++;
                        vals.put(c2, j);
                        vals.put(c3, (i + j + carry) % 10);
                        carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                    }
                    j = 0;
                    i++;
                    vals.put(c1, i);
                    vals.put(c2, j);
                    vals.put(c3, (i + j + carry) % 10);
                    carry = (Math.max(vals.get(c2) + i + carry - 10, 0) >= 0) ? 1 : 0;
                }
                if (i < 10) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    public static void main(String args[]) {
        Map<Character, Integer> m = Cryptarithmetic.solvePuzzle("FIVE", "SIX", "SEVEN");
    }
}
