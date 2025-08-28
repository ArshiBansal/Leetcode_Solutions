import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        // Bottom-left (including main diagonal)
        for (int startRow = 0; startRow < n; startRow++) {
            List<Integer> diag = new ArrayList<>();
            int i = startRow, j = 0;

            // collect
            while (i < n && j < n) {
                diag.add(grid[i][j]);
                i++;
                j++;
            }

            // sort descending
            diag.sort(Collections.reverseOrder());

            // put back
            i = startRow; j = 0;
            int k = 0;
            while (i < n && j < n) {
                grid[i][j] = diag.get(k++);
                i++;
                j++;
            }
        }

        // Top-right
        for (int startCol = 1; startCol < n; startCol++) {
            List<Integer> diag = new ArrayList<>();
            int i = 0, j = startCol;

            // collect
            while (i < n && j < n) {
                diag.add(grid[i][j]);
                i++;
                j++;
            }

            // sort ascending
            Collections.sort(diag);

            // put back
            i = 0; j = startCol;
            int k = 0;
            while (i < n && j < n) {
                grid[i][j] = diag.get(k++);
                i++;
                j++;
            }
        }

        return grid;
    }
}