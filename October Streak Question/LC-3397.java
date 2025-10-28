class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        long current = Long.MIN_VALUE;  // track next available integer
        int distinct = 0;

        for (int num : nums) {
            long left = (long) num - k;
            long right = (long) num + k;

            // ensure current starts within this range
            if (current < left) current = left;

            if (current <= right) {
                distinct++;
                current++; // move to next available integer
            }
        }
        return distinct;
    }
}