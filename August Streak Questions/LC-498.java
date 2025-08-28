class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 1;  // d = 1 means moving up-right, -1 means moving down-left

        for (int i = 0; i < m * n; i++) {
            result[i] = mat[row][col];

            // Moving up-right
            if (d == 1) {
                if (col == n - 1) { // hit right boundary
                    row++;
                    d = -1;
                } else if (row == 0) { // hit top boundary
                    col++;
                    d = -1;
                } else {
                    row--;
                    col++;
                }
            } 
            // Moving down-left
            else {
                if (row == m - 1) { // hit bottom boundary
                    col++;
                    d = 1;
                } else if (col == 0) { // hit left boundary
                    row++;
                    d = 1;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }
}