import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Sort robots
        Collections.sort(robot);

        // Sort factories by position
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        // Expand factory slots
        List<Integer> slots = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0], limit = f[1];
            for (int i = 0; i < limit; i++) {
                slots.add(pos);
            }
        }

        int n = robot.size();
        int m = slots.size();

        long[][] dp = new long[n + 1][m + 1];

        // Initialize
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        // DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Skip slot
                dp[i][j] = dp[i][j - 1];

                // Assign robot to slot
                long cost = Math.abs(robot.get(i - 1) - slots.get(j - 1));
                dp[i][j] = Math.min(dp[i][j],
                        dp[i - 1][j - 1] + cost);
            }
        }

        return dp[n][m];
    }
}