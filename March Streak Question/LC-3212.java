class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[] colX = new int[m];
        int[] colY = new int[m];

        int result = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'X') colX[j]++;
                else if (grid[i][j] == 'Y') colY[j]++;
            }

            int prefixX = 0;
            int prefixY = 0;

            for (int j = 0; j < m; j++) {
                prefixX += colX[j];
                prefixY += colY[j];

                if (prefixX == prefixY && prefixX > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}