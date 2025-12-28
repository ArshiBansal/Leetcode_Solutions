class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        int half = k / 2;

        // Base profit without modification
        long baseProfit = 0;
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }

        // Prepare arrays A and B
        long[] A = new long[n];
        long[] B = new long[n];

        for (int i = 0; i < n; i++) {
            A[i] = -(long) strategy[i] * prices[i];
            B[i] = (long) (1 - strategy[i]) * prices[i];
        }

        // Initial window
        long sumA = 0, sumB = 0;
        for (int i = 0; i < half; i++) {
            sumA += A[i];
        }
        for (int i = half; i < k; i++) {
            sumB += B[i];
        }

        long bestDelta = sumA + sumB;

        // Slide window
        for (int l = 1; l + k <= n; l++) {
            // update sumA
            sumA -= A[l - 1];
            sumA += A[l + half - 1];

            // update sumB
            sumB -= B[l + half - 1];
            sumB += B[l + k - 1];

            bestDelta = Math.max(bestDelta, sumA + sumB);
        }

        // Modification is optional
        return baseProfit + Math.max(0, bestDelta);
    }
}