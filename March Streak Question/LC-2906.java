class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;
        int MOD = 12345;

        // Flatten grid
        int[] arr = new int[size];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[idx++] = grid[i][j] % MOD;
            }
        }

        // Prefix product
        int[] prefix = new int[size];
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = (int)((long)prefix[i - 1] * arr[i - 1] % MOD);
        }

        // Suffix product
        int[] suffix = new int[size];
        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (int)((long)suffix[i + 1] * arr[i + 1] % MOD);
        }

        // Build result
        int[][] result = new int[n][m];
        idx = 0;
        for (int i = 0; i < size; i++) {
            int val = (int)((long)prefix[i] * suffix[i] % MOD);
            result[i / m][i % m] = val;
        }

        return result;
    }
}