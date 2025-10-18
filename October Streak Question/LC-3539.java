class Solution {
    private static final long MOD = 1_000_000_007L;

    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;

        // Precompute factorials and inverse factorials up to m
        long[] fact = new long[m + 1];
        long[] invFact = new long[m + 1];
        fact[0] = 1;
        for (int i = 1; i <= m; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[m] = modInverse(fact[m]);
        for (int i = m - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % MOD;

        // Precompute powers: powNums[j][t] = nums[j]^t % MOD for t=0..m
        long[][] powNums = new long[n][m + 1];
        for (int j = 0; j < n; j++) {
            powNums[j][0] = 1;
            long base = nums[j] % MOD;
            for (int t = 1; t <= m; t++) {
                powNums[j][t] = powNums[j][t - 1] * base % MOD;
            }
        }

        // dp over indices. We'll use 3D arrays and roll over j to j+1.
        // dp[used][carry][ones] = sum of products (∏ nums[j]^c / c! ) for processed indices
        int U = m;
        int C = m; // carry max
        int O = m;
        long[][][] dp = new long[U + 1][C + 1][O + 1];
        dp[0][0][0] = 1L;

        for (int j = 0; j < n; j++) {
            long[][][] ndp = new long[U + 1][C + 1][O + 1];
            for (int used = 0; used <= U; used++) {
                for (int carry = 0; carry <= C; carry++) {
                    for (int ones = 0; ones <= O; ones++) {
                        long cur = dp[used][carry][ones];
                        if (cur == 0) continue;
                        // choose c occurrences for index j
                        int maxC = U - used;
                        for (int c = 0; c <= maxC; c++) {
                            
                            int nused = used + c;
                            int total = c + carry; // sum at bit j
                            int bit = total & 1;
                            int non = ones + bit;
                            if (non > O) break; // ones overflow
                            int ncarry = total >>> 1;
                            if (ncarry > C) continue; // shouldn't happen as m small
                            // multiply by nums[j]^c / c!
                            long mul = powNums[j][c] * invFact[c] % MOD;
                            long add = cur * mul % MOD;
                            ndp[nused][ncarry][non] += add;
                            if (ndp[nused][ncarry][non] >= MOD) ndp[nused][ncarry][non] -= MOD;
                        }
                    }
                }
            }
            dp = ndp;
        }

        // Collect answers: used must be m; add remaining carry's popcount to ones
        long totalSum = 0;
        for (int carry = 0; carry <= C; carry++) {
            int pc = Integer.bitCount(carry);
            for (int ones = 0; ones <= O; ones++) {
                long val = dp[m][carry][ones];
                if (val == 0) continue;
                if (ones + pc == k) {
                    totalSum += val;
                    if (totalSum >= MOD) totalSum -= MOD;
                }
            }
        }

        // Multiply by m! (the multinomial outer factor)
        totalSum = totalSum * fact[m] % MOD;
        return (int) totalSum;
    }

    // fast modular exponent
    private static long modPow(long a, long e) {
        long r = 1 % MOD;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return r;
    }

    // modular inverse using Fermat (MOD prime)
    private static long modInverse(long x) {
        return modPow(x, MOD - 2);
    }
}
