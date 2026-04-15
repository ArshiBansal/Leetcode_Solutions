import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Step 1: Store indices for each value
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int minDist = Integer.MAX_VALUE;
        
        // Step 2: Process each value's indices
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;
            
            // Check consecutive triplets
            for (int i = 0; i <= list.size() - 3; i++) {
                int left = list.get(i);
                int right = list.get(i + 2);
                
                int dist = 2 * (right - left);
                minDist = Math.min(minDist, dist);
            }
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}