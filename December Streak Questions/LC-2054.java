import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;

        // Sort events by start time
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // maxFromRight[i] stores the maximum value from events[i] to events[n-1]
        int[] maxFromRight = new int[n];
        maxFromRight[n - 1] = events[n - 1][2];

        for (int i = n - 2; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], events[i][2]);
        }

        int maxSum = 0;

        for (int i = 0; i < n; i++) {
            // Option 1: take only this event
            maxSum = Math.max(maxSum, events[i][2]);

            // Option 2: take this event + the best non-overlapping future event
            int nextIndex = findNextEvent(events, events[i][1] + 1);
            if (nextIndex < n) {
                maxSum = Math.max(maxSum, events[i][2] + maxFromRight[nextIndex]);
            }
        }

        return maxSum;
    }

    // Binary search to find the first event with start time >= target
    private int findNextEvent(int[][] events, int target) {
        int left = 0, right = events.length - 1;
        int result = events.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
