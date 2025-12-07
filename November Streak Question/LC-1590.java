import java.util.HashMap;
import java.util.Map;
class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int num : nums) total += num;
        int rem = (int)(total % p);
        if (rem == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);  // prefix sum before array starts

        long prefix = 0;
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            int curr = (int)(prefix % p);

            // We want some earlier prefix i where:
            // earlier % p == (curr - rem + p) % p
            int target = (curr - rem + p) % p;

            if (map.containsKey(target)) {
                int len = i - map.get(target);
                if (len < ans) ans = len;
            }

            map.put(curr, i);
        }

        return ans == nums.length ? -1 : ans;
    }
}
