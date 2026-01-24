class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int lp = 0; 
        int rp = nums.length - 1;
        while (lp < rp) {
            sum = Math.max(sum, nums[lp]+nums[rp]);
            lp++;
            rp--;
        }
        return sum;
    }
}