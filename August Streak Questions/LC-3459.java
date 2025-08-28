class Solution {
    private static final int[][] DIRS = {
        {1, 1},   // down-right
        {1, -1},  // down-left
        {-1, -1}, // up-left
        {-1, 1}   // up-right
    };

    // Maps each direction to its clockwise rotated direction
    private static final int[] CLOCKWISE = {1, 2, 3, 0};

    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 1) continue;

                // Try all 4 starting diagonal directions
                for (int dir = 0; dir < 4; dir++) {
                    ans = Math.max(ans, explore(grid, i, j, dir));
                }
            }
        }
        return ans;
    }

    private int explore(int[][] grid, int i, int j, int dir) {
        int n = grid.length, m = grid[0].length;
        int length = 1; // starting at 1
        int maxLen = 1;

        int x = i, y = j;
        int nextExpected = 2; // after 1, expect 2

        // Follow straight diagonal
        while (true) {
            x += DIRS[dir][0];
            y += DIRS[dir][1];

            if (x < 0 || x >= n || y < 0 || y >= m) break;
            if (grid[x][y] != nextExpected) break;

            length++;
            maxLen = Math.max(maxLen, length);

            // Try a clockwise turn from here
            int turnLen = length + tryTurn(grid, x, y, CLOCKWISE[dir], (nextExpected == 2 ? 0 : 2));
            maxLen = Math.max(maxLen, turnLen);

            // Alternate expectation
            nextExpected = (nextExpected == 2 ? 0 : 2);
        }
        return maxLen;
    }

    private int tryTurn(int[][] grid, int i, int j, int dir, int nextExpected) {
        int n = grid.length, m = grid[0].length;
        int len = 0;
        int x = i, y = j;

        while (true) {
            x += DIRS[dir][0];
            y += DIRS[dir][1];

            if (x < 0 || x >= n || y < 0 || y >= m) break;
            if (grid[x][y] != nextExpected) break;

            len++;
            nextExpected = (nextExpected == 2 ? 0 : 2);
        }
        return len;
    }
}
