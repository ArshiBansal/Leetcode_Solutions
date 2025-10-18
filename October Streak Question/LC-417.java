import java.util.*;

class Solution {
    private int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}}; // down, up, right, left

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacificReachable = new boolean[m][n];
        boolean[][] atlanticReachable = new boolean[m][n];

        // DFS from Pacific Ocean (top row and left column)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacificReachable, i, 0);
            dfs(heights, atlanticReachable, i, n - 1); // Atlantic right column
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacificReachable, 0, j); // Pacific top row
            dfs(heights, atlanticReachable, m - 1, j); // Atlantic bottom row
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void dfs(int[][] heights, boolean[][] reachable, int i, int j) {
        if (reachable[i][j]) return;
        reachable[i][j] = true;
        for (int[] dir : directions) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < heights.length && y >= 0 && y < heights[0].length
                && heights[x][y] >= heights[i][j]) { // reverse flow condition
                dfs(heights, reachable, x, y);
            }
        }
    }
}

