class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int b : batteries) sum += b;

        long left = 0;
        long right = sum / n;  // Maximum possible time

        while (left < right) {
            long mid = (left + right + 1) / 2; // test candidate time

            if (canRun(mid, n, batteries)) {
                left = mid; // feasible → try bigger T
            } else {
                right = mid - 1; // infeasible → reduce T
            }
        }

        return left;
    }

    private boolean canRun(long T, int n, int[] batteries) {
        long total = 0;

        for (int b : batteries) {
            total += Math.min(b, T);
            if (total >= (long)n * T) return true; // already enough power
        }

        return total >= (long)n * T;
    }
}
