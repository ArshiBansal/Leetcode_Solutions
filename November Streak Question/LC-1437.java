class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int prev = -1;  // track index of previous 1

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 1) {
            if (prev != -1 && i - prev - 1 < k) {
                return false;  // distance between consecutive 1's is too small
            }
            prev = i;
        }
    }

    return true;
    }
}