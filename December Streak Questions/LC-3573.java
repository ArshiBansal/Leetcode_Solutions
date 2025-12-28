class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n < 2 || k == 0) return 0L;

        long[][] dp = new long[k + 1][n];

        for (int t = 1; t <= k; t++) {
            long maxDiffNormal = -prices[0];  // dp[t-1][j] - prices[j]
            long maxDiffShort = prices[0];    // dp[t-1][j] + prices[j]

            for (int i = 1; i < n; i++) {
                dp[t][i] = dp[t][i - 1];  // No transaction today

                // Normal transaction: buy before i, sell at i
                dp[t][i] = Math.max(dp[t][i], prices[i] + maxDiffNormal);

                // Short sell: sell before i, buy at i
                dp[t][i] = Math.max(dp[t][i], -prices[i] + maxDiffShort);

                // Update maxDiffs using i - 1 (i must be > 0 to have previous day)
                maxDiffNormal = Math.max(maxDiffNormal, dp[t - 1][i - 1] - prices[i]);
                maxDiffShort = Math.max(maxDiffShort, dp[t - 1][i - 1] + prices[i]);
            }
        }

        return dp[k][n - 1];
    }
}
