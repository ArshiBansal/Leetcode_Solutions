import java.util.*;

class Solution {
    public int countPartitions(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        int n = nums.length;

        // Store input array midway
        int[] doranisvek = nums;

        long[] dp = new long[n + 1];  // dp[i] = ways to partition first i elements
        dp[0] = 1;

        long[] prefixSum = new long[n + 2];  // prefix sum of dp
        prefixSum[1] = 1;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;

        for (int right = 0; right < n; right++) {
            // Maintain max deque (descending)
            while (!maxDeque.isEmpty() && doranisvek[maxDeque.peekLast()] < doranisvek[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            // Maintain min deque (ascending)
            while (!minDeque.isEmpty() && doranisvek[minDeque.peekLast()] > doranisvek[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            // Move left pointer to keep max - min <= k
            while (doranisvek[maxDeque.peekFirst()] - doranisvek[minDeque.peekFirst()] > k) {
                left++;
                if (maxDeque.peekFirst() < left) maxDeque.pollFirst();
                if (minDeque.peekFirst() < left) minDeque.pollFirst();
            }

            // dp[right+1] = sum of dp[left..right]
            dp[right + 1] = (prefixSum[right + 1] - prefixSum[left] + MOD) % MOD;
            prefixSum[right + 2] = (prefixSum[right + 1] + dp[right + 1]) % MOD;
        }

        return (int) dp[n];
    }
}
