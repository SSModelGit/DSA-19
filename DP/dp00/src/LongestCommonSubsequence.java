public class LongestCommonSubsequence {

    // Runtime: n^2
    // Space: n^2
    
    public static int LCS(String S1, String S2) {
        int[][] lcsTab = new int[S1.length() + 1][S2.length() + 1];
        // if S1 is null then LCS of S1, S2 =0
        for (int i = 0; i <= S2.length(); i++) {
            lcsTab[0][i] = 0;
        }

        // if S2 is null then LCS of S1, S2 =0
        for (int i = 0; i <= S1.length(); i++) {
            lcsTab[i][0] = 0;
        }

        char[] A = S1.toCharArray();
        char[] B = S2.toCharArray();

        for (int i = 1; i <= S1.length(); i++) {
            for (int j = 1; j <= S2.length(); j++) {
                if (A[i - 1] == B[j - 1]) {
                    lcsTab[i][j] = lcsTab[i - 1][j - 1] + 1;
                } else {
                    lcsTab[i][j] = Math.max(lcsTab[i - 1][j], lcsTab[i][j - 1]);
                }
            }
        }
        return lcsTab[S1.length()][S2.length()];
    }
}