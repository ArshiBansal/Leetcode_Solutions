class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        // dp[i][j][c] = max score reaching (i,j) with cost c
        int[][][] dp = new int[m][n][k + 1];
        
        // Initialize with -1 (unreachable)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }
        
        // Start point
        dp[0][0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (dp[i][j][c] == -1) continue;
                    
                    // Move Right
                    if (j + 1 < n) {
                        int cost = (grid[i][j + 1] == 0) ? 0 : 1;
                        int newCost = c + cost;
                        
                        if (newCost <= k) {
                            int newScore = dp[i][j][c] + grid[i][j + 1];
                            dp[i][j + 1][newCost] = Math.max(dp[i][j + 1][newCost], newScore);
                        }
                    }
                    
                    // Move Down
                    if (i + 1 < m) {
                        int cost = (grid[i + 1][j] == 0) ? 0 : 1;
                        int newCost = c + cost;
                        
                        if (newCost <= k) {
                            int newScore = dp[i][j][c] + grid[i + 1][j];
                            dp[i + 1][j][newCost] = Math.max(dp[i + 1][j][newCost], newScore);
                        }
                    }
                }
            }
        }
        
        int result = -1;
        for (int c = 0; c <= k; c++) {
            result = Math.max(result, dp[m - 1][n - 1][c]);
        }
        
        return result;
    }
}