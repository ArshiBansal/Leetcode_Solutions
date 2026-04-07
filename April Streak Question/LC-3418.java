class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;

        int[][] dp = new int[n][3];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < 3; k++) {
                dp[j][k] = Integer.MIN_VALUE;
            }
        }

        // Start (0,0)
        if (coins[0][0] >= 0) {
            dp[0][0] = coins[0][0];
        } else {
            dp[0][0] = coins[0][0];
            dp[0][1] = 0;
        }

        // First row
        for (int j = 1; j < n; j++) {
            for (int k = 0; k < 3; k++) {
                if (dp[j - 1][k] == Integer.MIN_VALUE) continue;

                int val = coins[0][j];

                // no neutralization
                dp[j][k] = Math.max(dp[j][k], dp[j - 1][k] + val);

                // neutralize
                if (val < 0 && k < 2) {
                    dp[j][k + 1] = Math.max(dp[j][k + 1], dp[j - 1][k]);
                }
            }
        }

        // Remaining rows
        for (int i = 1; i < m; i++) {
            int[][] newDp = new int[n][3];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    newDp[j][k] = Integer.MIN_VALUE;
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {

                    // FROM TOP
                    if (dp[j][k] != Integer.MIN_VALUE) {
                        int val = coins[i][j];

                        // no neutralization
                        newDp[j][k] = Math.max(newDp[j][k], dp[j][k] + val);

                        // neutralize
                        if (val < 0 && k < 2) {
                            newDp[j][k + 1] = Math.max(newDp[j][k + 1], dp[j][k]);
                        }
                    }

                    // FROM LEFT
                    if (j > 0 && newDp[j - 1][k] != Integer.MIN_VALUE) {
                        int val = coins[i][j];

                        // no neutralization
                        newDp[j][k] = Math.max(newDp[j][k], newDp[j - 1][k] + val);

                        // neutralize
                        if (val < 0 && k < 2) {
                            newDp[j][k + 1] = Math.max(newDp[j][k + 1], newDp[j - 1][k]);
                        }
                    }
                }
            }

            dp = newDp;
        }

        return Math.max(dp[n - 1][0],
               Math.max(dp[n - 1][1], dp[n - 1][2]));
    }
}