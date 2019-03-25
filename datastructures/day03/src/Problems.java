import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        List<Integer> l = new LinkedList<>();

        int counter = 0; // total length of new string
        int hIdx = k; // index of header
        int fIdx = k; // index of following points

        for (int i = k-1; i >= 0; i--) {
            if (A[i] < A[hIdx] || A[i] == 0 || A[i] == 1) {
                if (l.isEmpty()) {
                    if (A[i] < A[hIdx]) {
                    hIdx = i;
                    l.add(0,A[i]);
                    counter++;

                    if (k + counter < A.length) {
                        if (A[fIdx] >= A[k+counter]) {
                            fIdx = k + counter;
                        }
                    }
                }}
                else if (A[i] <= l.get(0)) {
                    hIdx = i;
                    l.add(0,A[i]);
                    counter++;

                    if (k + counter < A.length) {
                        if (A[fIdx] >= A[k+counter]) {
                            fIdx = k + counter;
                        }
                    }
                }
            }
        }
        l.add(A[fIdx]);
        counter++;
        fIdx++;
        
        if (A.length - fIdx == A.length - k - counter) {
            while (counter - fIdx > 0) {}
            for (int j = fIdx; j < A.length; j++) {
                if (counter < A.length - k) {
                    l.add(A[j]);
                    counter++;
                }
            }
        }
        else if (A.length - k - counter < 0) {
            for (int h = counter - A.length + k; h > 0; h--) {
                l.remove(counter-1);
                counter--;
            }
        }
        else if (A.length - fIdx > A.length - k - counter) {
            List<Integer> lnew = removeKDigits(Arrays.copyOfRange(A, fIdx, A.length), A.length - k - counter);
            l.addAll(lnew);
        }

        return l;
    }

    public static boolean isPalindrome(Node n) {
        int size;
        if (n == null) {
            return true;
        }
        else {
            size = 1;
        }
        Node current = n;
        Node middle = n;
        Node middleprior = null;
        Node temp = null;
        while (current.next != null) {
            current = current.next;
            size++;
            if (size%2 == 1) {
                temp = middle.next;
                if (middleprior != null) {
                    middle.next = middleprior;
                    middleprior = middle;
                }
                else {
                    middleprior = middle;
                    middleprior.next = null;
                }
                middle = temp;
            }
        }
        if (size == 1) {
            return true;
        }

        if (size%2 == 0) {
            if (middle.val != middle.next.val) {
                return false;
            }
            current = middle.next.next;
            while (current != null && middleprior != null) {
                if (current.val != middleprior.val) {
                    return false;
                }
                current = current.next;
                middleprior = middleprior.next;
            }
            if (middleprior == null && current == null) {
                return true;
            }
            return false;
        }
        else {
            current = middle.next;
            while (current != null && middleprior != null) {
                if (current.val != middleprior.val) {
                    return false;
                }
                current = current.next;
                middleprior = middleprior.next;
            }
            if (middleprior == null && current == null) {
                return true;
            }
            return false;
        }
    }

    public static String infixToPostfix(String s) {
        int count = 0;
        int openQ = 0;
        String opString = "";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openQ++;
            }
            if (s.charAt(i) == ')') {
                if (count < opString.length()) {
                    if (res.length() > 0) {
                        if (res.charAt(res.length() -1) != ' '){
                            res = res + ' ';
                        }
                    }
                    res = res + opString.charAt(count);
                    count++;
                }
                openQ--;
            }
            if (s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4') { 
                if (res.length() > 0) {
                    if (res.charAt(res.length() -1) != ' '){
                        res = res + ' ';
                    }
                }
                res = res + s.charAt(i) + " ";
            }
            if (s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8') {
                if (res.length() > 0) {
                    if (res.charAt(res.length() -1) != ' '){
                        res = res + ' ';
                    }
                }
                res = res + s.charAt(i) + " ";
            }
            if (s.charAt(i) == '9' || s.charAt(i) == '0') {
                if (res.length() > 0) {
                    if (res.charAt(res.length() -1) != ' '){
                        res = res + ' ';
                    }
                }
                res = res + s.charAt(i) + " ";
            }
            if (s.charAt(i) == '+' || s.charAt(i) == '*') {
                if (openQ > 1) {
                    opString = s.charAt(i) + opString;
                }
                else {
                    opString = opString + s.charAt(i) ;
                }
            }
        }
        return res;
    }

}
