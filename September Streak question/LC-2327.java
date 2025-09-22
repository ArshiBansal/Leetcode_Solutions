class Solution {
    int MOD = 1_000_000_007;
    
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n + 1]; 
        dp[1] = 1; // day 1, one person knows

        long share = 0; // number of people currently able to share

        for (int day = 2; day <= n; day++) {
            // people start sharing today
            if (day - delay >= 1) {
                share = (share + dp[day - delay]) % MOD;
            }
            // people forget today
            if (day - forget >= 1) {
                share = (share - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = share; // new people who learn today
        }

        long ans = 0;
        // sum people who still remember on day n
        for (int day = n - forget + 1; day <= n; day++) {
            if (day >= 1) {
                ans = (ans + dp[day]) % MOD;
            }
        }
        return (int) ans;
    }
}