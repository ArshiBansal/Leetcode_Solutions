class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 0;
        long right = (long)1e18;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long totalHeight = 0;

            for (int t : workerTimes) {
                double val = Math.sqrt(1.0 + (8.0 * mid) / t);
                long x = (long)((val - 1) / 2);
                totalHeight += x;

                if (totalHeight >= mountainHeight) break;
            }

            if (totalHeight >= mountainHeight) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }
}