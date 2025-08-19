class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;   // total count of zero-filled subarrays
        long streak = 0;  // length of current consecutive zero block

        for (int num : nums) {
            if (num == 0) {
                streak++;          // extend zero streak
                count += streak;   // add new subarrays ending here
            } else {
                streak = 0;        // reset when non-zero appears
            }
        }

        return count;
    }
}