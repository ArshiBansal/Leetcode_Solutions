class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxFruits = 0;
        int left = 0, total = 0;

        for (int right = 0; right < n; right++) {
            total += fruits[right][1]; // Add current fruit amount

            // Shrink the window if it's not reachable within k steps
            while (left <= right && !isReachable(fruits[left][0], fruits[right][0], startPos, k)) {
                total -= fruits[left][1];
                left++;
            }

            maxFruits = Math.max(maxFruits, total);
        }

        return maxFruits;
    }

    // Helper method to check if the window [lPos, rPos] is reachable within k steps
    private boolean isReachable(int lPos, int rPos, int startPos, int k) {
        int goLeftThenRight = Math.abs(startPos - lPos) + (rPos - lPos);
        int goRightThenLeft = Math.abs(rPos - startPos) + (rPos - lPos);
        return Math.min(goLeftThenRight, goRightThenLeft) <= k;
    }
}
