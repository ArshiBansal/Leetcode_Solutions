class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts) return 1.0; // Alice always wins

        double[] dp = new double[n + 1];
        dp[0] = 1.0;
        double Wsum = 1.0, result = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Wsum / maxPts; // Probability from sliding window sum

            if (i < k) Wsum += dp[i]; // Add current probability
            else result += dp[i]; // If i >= k, add to result

            if (i >= maxPts) Wsum -= dp[i - maxPts]; // Remove old probability
        }

        return result;
    }
}
