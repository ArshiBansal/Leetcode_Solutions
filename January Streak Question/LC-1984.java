import java.util.*;
class Solution {
    public int minimumDifference(int[] nums, int k) {
        if(k==1) return 0;
        Arrays.sort(nums);
        int minDiff=Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int diff = nums[i + k - 1] - nums[i]; // Step 2: Compute difference in window
            minDiff = Math.min(minDiff, diff); // Step 3: Track minimum difference
        }

        return minDiff;
    }
}