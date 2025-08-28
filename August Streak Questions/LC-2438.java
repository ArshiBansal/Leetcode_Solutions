class Solution {
    static final int MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        // Step 1: Build the powers array from set bits of n
        java.util.List<Long> powersList = new java.util.ArrayList<>();
        for (int i = 0; i < 31; i++) { // because 2^30 > 1e9
            if ((n & (1 << i)) != 0) {
                powersList.add(1L << i);
            }
        }

        // Step 2: Build prefix products modulo MOD
        int m = powersList.size();
        long[] prefix = new long[m];
        prefix[0] = powersList.get(0) % MOD;
        for (int i = 1; i < m; i++) {
            prefix[i] = (prefix[i - 1] * (powersList.get(i) % MOD)) % MOD;
        }

        // Step 3: Answer queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            if (l == 0) {
                ans[i] = (int) prefix[r];
            } else {
                ans[i] = (int) ((prefix[r] * modPow(prefix[l - 1], MOD - 2)) % MOD);
            }
        }
        return ans;
    }

    // Fast exponentiation to find modular inverse (Fermat's little theorem)
    private long modPow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}
