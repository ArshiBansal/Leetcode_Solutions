class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        int[] count = new int[value];
        
        // Count frequencies of remainders
        for (int num : nums) {
            int r = ((num % value) + value) % value;
            count[r]++;
        }

        int mex = 0;
        while (true) {
            int r = mex % value;
            if (count[r] > 0) {
                count[r]--;
                mex++;
            } else {
                return mex;
            }
        }
    }
}