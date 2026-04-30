import java.util.*;

public class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];

        // Step 1: group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Step 2: process each group
        for (List<Integer> indices : map.values()) {
            int k = indices.size();
            if (k == 1) continue;

            // prefix sum array
            long[] prefix = new long[k + 1];
            for (int i = 0; i < k; i++) {
                prefix[i + 1] = prefix[i] + indices.get(i);
            }

            // compute result for each index
            for (int i = 0; i < k; i++) {
                long idx = indices.get(i);

                // left side
                long left = idx * i - prefix[i];

                // right side
                long right = (prefix[k] - prefix[i + 1]) - idx * (k - i - 1);

                result[(int) idx] = left + right;
            }
        }

        return result;
    }
}