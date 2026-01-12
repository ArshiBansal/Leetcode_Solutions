class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // dp[i][j] will store the minimum ASCII delete sum for s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Initialize first column (delete all chars from s1)
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        // Initialize first row (delete all chars from s2)
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Characters match, no deletion needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Either delete from s1 or s2, take the min
                    dp[i][j] = Math.min(
                        dp[i - 1][j] + s1.charAt(i - 1), // delete from s1
                        dp[i][j - 1] + s2.charAt(j - 1)  // delete from s2
                    );
                }
            }
        }

        return dp[m][n];
    }
}