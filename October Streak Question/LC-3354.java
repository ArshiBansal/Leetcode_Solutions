class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int validCount = 0;

        // Try starting from every zero position
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // Try both directions: left (-1) and right (+1)
                if (simulate(nums, i, -1)) validCount++;
                if (simulate(nums, i, 1)) validCount++;
            }
        }

        return validCount;
    }

    // Helper function to simulate the process
    private boolean simulate(int[] nums, int start, int dir) {
        int[] arr = nums.clone(); // create a copy to avoid modifying the original
        int n = arr.length;
        int curr = start;

        while (curr >= 0 && curr < n) {
            if (arr[curr] == 0) {
                curr += dir; // move in the current direction
            } else {
                arr[curr]--;   // decrement the number
                dir = -dir;    // reverse direction
                curr += dir;   // move one step
            }
        }

        // Check if all elements became 0
        for (int x : arr) {
            if (x != 0) return false;
        }
        return true;
    }
}
