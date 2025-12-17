import java.util.*;
class Solution {
    public int specialTriplets(int[] nums) {
        final long MOD = 1_000_000_007L;
        int n = nums.length;

        // Frequency of all numbers to the right initially
        Map<Integer, Integer> right = new HashMap<>();
        for (int x : nums) {
            right.put(x, right.getOrDefault(x, 0) + 1);
        }

        Map<Integer, Integer> left = new HashMap<>();
        long result = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];

            // Remove nums[j] from right map (it is now the "current middle")
            right.put(x, right.get(x) - 1);
            if (right.get(x) == 0) right.remove(x);

            int need = 2 * x;

            long leftCount = left.getOrDefault(need, 0);
            long rightCount = right.getOrDefault(need, 0);

            result = (result + (leftCount * rightCount) % MOD) % MOD;

            // Add nums[j] to left map
            left.put(x, left.getOrDefault(x, 0) + 1);
        }

        return (int) result;
    }
}