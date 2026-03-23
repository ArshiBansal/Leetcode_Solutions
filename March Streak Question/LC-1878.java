class Solution {

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int first = Integer.MIN_VALUE; 
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                int top = row + 1;
                int bottom = m - row;
                int left = col + 1;
                int right = n - col;

                int size = Math.min(Math.min(top, bottom), Math.min(left, right));

                for(int layer = 0; layer < size; layer++){ 
                    int sum = calculateSum(layer, row, col, grid);
                    if (sum == first || sum == second || sum == third) {
                        continue;
                    }
                    if (sum > first) {
                        third = second;
                        second = first;
                        first = sum;
                    } else if (sum > second) {
                        third = second;
                        second = sum;
                    } else if (sum > third) {
                        third = sum;
                    }

                }

            }
        }

        // Count how many valid top sums we actually have
        int count = 0;
        if (first != Integer.MIN_VALUE) count++;
        if (second != Integer.MIN_VALUE) count++;
        if (third != Integer.MIN_VALUE) count++;

        // Create array of the correct size
        int[] resultArray = new int[count];

        // Fill it (in order: first, second, third)
        int writeIndex = 0;

        if (first != Integer.MIN_VALUE) {
            resultArray[writeIndex] = first;
            writeIndex++;
        }
        if (second != Integer.MIN_VALUE) {
            resultArray[writeIndex] = second;
            writeIndex++;
        }
        if (third != Integer.MIN_VALUE) {
            resultArray[writeIndex] = third;
            writeIndex++;
        }
        return resultArray;
    }

    public int calculateSum(int s, int r, int c, int[][] grid) {
        //base case
        if (s == 0) {
            return grid[r][c];
        }

        int sum = 0;

        for (int i = 0; i < s; i++) {
            sum += grid[r - s + i][c + i];
        }

        for (int i = 0; i < s; i++) {
            sum += grid[r + i][c + s - i];
        }

        for (int i = 0; i < s; i++) {
            sum += grid[r + s - i][c - i];
        }

        for (int i = 0; i < s; i++) {
            sum += grid[r - i][c - s + i];
        }

        return sum;
    }

}