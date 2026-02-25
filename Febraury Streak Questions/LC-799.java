class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[100][100];
        dp[0][0] = poured;
        
        for (int r = 0; r < 99; r++) {
            for (int c = 0; c <= r; c++) {
                if (dp[r][c] > 1) {
                    double overflow = (dp[r][c] - 1) / 2.0;
                    dp[r+1][c] += overflow;
                    dp[r+1][c+1] += overflow;
                    dp[r][c] = 1;
                }
            }
        }
        
        return Math.min(1, dp[query_row][query_glass]);
        
    }
}