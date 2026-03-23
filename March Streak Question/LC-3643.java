class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int top = x;
        int bottom = x + k - 1;

        while (top < bottom) {
            for (int col = 0; col < k; col++) {
                int temp = grid[top][y + col];
                grid[top][y + col] = grid[bottom][y + col];
                grid[bottom][y + col] = temp;
            }
            top++;
            bottom--;
        }

        return grid;
    }
}