import java.util.*;
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, Integer> rowMin = new HashMap<>();
        Map<Integer, Integer> rowMax = new HashMap<>();
        Map<Integer, Integer> colMin = new HashMap<>();
        Map<Integer, Integer> colMax = new HashMap<>();

        // Track extremes for each row and column
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            rowMin.put(x, Math.min(rowMin.getOrDefault(x, Integer.MAX_VALUE), y));
            rowMax.put(x, Math.max(rowMax.getOrDefault(x, Integer.MIN_VALUE), y));
            colMin.put(y, Math.min(colMin.getOrDefault(y, Integer.MAX_VALUE), x));
            colMax.put(y, Math.max(colMax.getOrDefault(y, Integer.MIN_VALUE), x));
        }

        int covered = 0;

        // Check each building
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            boolean hasLeft  = rowMin.get(x) < y;
            boolean hasRight = rowMax.get(x) > y;
            boolean hasUp    = colMin.get(y) < x;
            boolean hasDown  = colMax.get(y) > x;

            if (hasLeft && hasRight && hasUp && hasDown) {
                covered++;
            }
        }

        return covered;
    }
}