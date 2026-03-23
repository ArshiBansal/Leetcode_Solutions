class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] prefix = new int[m][n];

        // Compute 2D prefix sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i][j] = grid[i][j];
                if (i > 0) prefix[i][j] += prefix[i-1][j];
                if (j > 0) prefix[i][j] += prefix[i][j-1];
                if (i > 0 && j > 0) prefix[i][j] -= prefix[i-1][j-1];
            }
        }

        int count = 0;
        // Iterate over all possible bottom-right corners of submatrices starting at (0,0)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = prefix[i][j]; // sum of submatrix (0,0) to (i,j)
                if (sum <= k) {
                    count++;
                }
            }
        }
        return count;
    }
}