import java.util.Arrays;
class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[i] will store the maximum sum with remainder i when divided by 3
        int[] dp = new int[3];
        Arrays.fill(dp, 0);

        for (int num : nums) {
            int[] temp = dp.clone(); // use a temporary array to store updates
            for (int i = 0; i < 3; i++) {
                int sum = dp[i] + num;
                temp[sum % 3] = Math.max(temp[sum % 3], sum);
            }
            dp = temp; // update dp
        }

        return dp[0]; // max sum divisible by 3
    }
}