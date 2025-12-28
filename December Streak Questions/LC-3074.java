import java.util.*;
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }

        // Sort capacities in descending order
        Arrays.sort(capacity);
        int n = capacity.length;
        int boxesUsed = 0;
        int currentCapacity = 0;

        for (int i = n - 1; i >= 0; i--) {
            currentCapacity += capacity[i];
            boxesUsed++;
            if (currentCapacity >= totalApples) {
                return boxesUsed;
            }
        }

        // Guaranteed by problem constraints, so we won't reach here
        return boxesUsed;
    }
}