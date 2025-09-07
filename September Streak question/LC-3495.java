class Solution {
    static long[] pow4;

    // Precompute powers of 4 up to slightly above 1e9
    static {
        pow4 = new long[20];
        pow4[0] = 1;
        for (int i = 1; i < 20; i++) {
            pow4[i] = pow4[i - 1] * 4;
        }
    }

    // Computes sum of f(x) for x in [1, n]
    private long prefixSum(long n) {
        if (n <= 0) return 0;
        long total = 0;
        for (int k = 0; pow4[k] <= n; k++) {
            long left = pow4[k];
            long right = Math.min(n, pow4[k + 1] - 1);
            long count = right - left + 1;
            total += count * (k + 1);
        }
        return total;
    }

    // Computes sum of f(x) for x in [l, r]
    private long rangeSum(long l, long r) {
        return prefixSum(r) - prefixSum(l - 1);
    }

    public long minOperations(int[][] queries) {
        long result = 0;
        for (int[] q : queries) {
            long l = q[0], r = q[1];
            long totalSteps = rangeSum(l, r);
            result += (totalSteps + 1) / 2; // ceil division
        }
        return result;
    }
}
