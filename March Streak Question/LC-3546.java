class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        // Calculate total sum
        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
            }
        }

        // If total sum is odd, cannot split equally
        if (totalSum % 2 != 0) return false;

        // Check horizontal cuts
        long prefixSum = 0;
        for (int i = 0; i < m - 1; i++) { // ensure bottom part non-empty
            for (int j = 0; j < n; j++) {
                prefixSum += grid[i][j];
            }
            if (prefixSum * 2 == totalSum) return true;
        }

        // Check vertical cuts
        prefixSum = 0;
        for (int j = 0; j < n - 1; j++) { // ensure right part non-empty
            for (int i = 0; i < m; i++) {
                prefixSum += grid[i][j];
            }
            if (prefixSum * 2 == totalSum) return true;
        }

        return false;
    }
}