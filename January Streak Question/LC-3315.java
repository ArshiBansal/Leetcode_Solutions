import java.util.*;
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int result[] = new int[n];
        for(int i = 0; i < n; i++){
            int num = nums.get(i);

            int x = -1; // Initialise with -1, works when nothing is found
            int d = 1;

            // Check for the leftmost set bit in trailing ones
            while((num & d) != 0){
                x = num - d;
                d <<= 1;
            }

            result[i] = x;
        }
        return result;
    }
}