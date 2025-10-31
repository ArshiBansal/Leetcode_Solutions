class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length, idx = 0;
        int arr[] = new int[2]; 
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (i != j && nums[i] == nums[j]) {
                    if (idx < arr.length) { arr[idx] = nums[i];
                        idx++;
                    }
                }
            }
        }
        return arr; 
    }
}
