class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        for (int i = 0; i <= n - k; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            
            // Count frequencies for subarray nums[i..i+k-1]
            for (int j = i; j < i + k; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            }
            
            // Sort elements by frequency desc, then by value desc
            List<int[]> freqList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                freqList.add(new int[]{entry.getKey(), entry.getValue()});
            }
            
            freqList.sort((a, b) -> {
                if (b[1] != a[1]) return b[1] - a[1]; // Higher frequency first
                return b[0] - a[0]; // Tie-break by higher value
            });
            
            // Keep only top x most frequent elements
            Set<Integer> topX = new HashSet<>();
            for (int j = 0; j < Math.min(x, freqList.size()); j++) {
                topX.add(freqList.get(j)[0]);
            }
            
            // Calculate x-sum
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                if (topX.contains(nums[j])) sum += nums[j];
            }
            
            result[i] = sum;
        }
        
        return result;
    }
}