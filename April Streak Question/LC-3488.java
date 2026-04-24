import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // Step 1: Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();

        // Step 2: Process each query
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);

            // If only one occurrence
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }

            // Binary search to find position of q
            int pos = Collections.binarySearch(list, q);

            int size = list.size();

            // Get neighbors in circular list
            int leftIdx = list.get((pos - 1 + size) % size);
            int rightIdx = list.get((pos + 1) % size);

            // Compute circular distances
            int distLeft = getDist(q, leftIdx, n);
            int distRight = getDist(q, rightIdx, n);

            result.add(Math.min(distLeft, distRight));
        }

        return result;
    }

    private int getDist(int i, int j, int n) {
        int diff = Math.abs(i - j);
        return Math.min(diff, n - diff);
    }
}