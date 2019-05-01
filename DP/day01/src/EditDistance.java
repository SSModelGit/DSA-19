public class EditDistance {

    public static int minEditDist(String a, String b) {
        if (a.length() == 0 || b.length() == 0)
            return a.length() + b.length(); // Check for empty strings
        int[][] AB = new int[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                AB[i][j] = -1;
            }
        }
        return mED(a.toCharArray(), b.toCharArray(), a.length()-1, b.length()-1, AB);
    }

    private static int mED(char[] a, char[] b, int i, int j, int[][] AB) {
        // System.out.println("Index: " + i + ", " + j);
        if (i < 0 && j < 0) return 0;
        if (i < 0 || j < 0) return 1000;
        if (AB[i][j] != -1) return AB[i][j];
        if (a[i] == b[j])
            AB[i][j] = (mED(a, b, i-1, j-1, AB) >= 1000) ? Math.min(mED(a, b, i, j-1, AB), mED(a, b, i-1, j, AB)) + 1 : mED(a, b, i-1, j-1, AB);
        else
            AB[i][j] = Math.min(Math.min(mED(a, b, i, j-1, AB), mED(a, b, i-1, j, AB)), mED(a, b, i-1, j-1, AB)) + 1;
        return AB[i][j];
    }

    public static void main(String[] args) {
        System.out.println(minEditDist("aabbcdef", "abbcdef"));
    }

}
