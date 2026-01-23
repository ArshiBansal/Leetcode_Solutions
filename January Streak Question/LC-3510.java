class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        // Use long to prevent integer overflow when summing elements
        long[] num = new long[n];
        for(int i = 0; i<n; i++) num[i] = nums[i];

        // Simulated Doubly Linked List
        int[] L = new int[n+1];
        int[] R = new int[n+1];

        for(int i = 0; i<n; i++) {
            L[i] = i-1;
            R[i] = i+1;
        }

        // Min-Heap: Stores {sum, index}
        // Orders by smallest sum, ties broken by smallest index (leftmost)
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        boolean[] remove = new boolean[n];
        int bad = 0; // Count of inversions

        // Initial population
        for(int i = 0; i<n-1; i++) {
            pq.offer(new long[]{num[i]+num[i+1], i});
            if(num[i] > num[i+1]) bad++;
        }

        // If already sorted, 0 ops
        if(bad == 0) return 0;

        int op = 0;
        while(bad > 0) {
            long[] b = pq.poll();

            long sum = b[0];
            int i = (int)b[1];
            int j = R[i]; // The current right neighbor of i

            // 1. Validation: Skip if i is removed, j is out of bounds, 
            // or if the heap entry is stale (sum doesn't match current reality)
            if(remove[i] || j == n) continue;
            if(sum != num[i] + num[j]) continue;

            // 2. Remove contribution of old connections to 'bad' count
            if(L[i] != -1 && num[L[i]] > num[i]) bad--;
            if(num[i] > num[j]) bad--;
            if(R[j] != n && num[j] > num[R[j]]) bad--;

            // 3. Perform Merge
            remove[j] = true; // Delete j
            
            // Re-link: i connects to R[j]
            if(R[j] != n && L[R[j]] != -1) L[R[j]] = i; 
            R[i] = R[j];
            
            num[i] = sum; // Update value of i

            // 4. Add contribution of new connections to 'bad' count
            if(L[i] != -1 && num[L[i]] > num[i]) bad++;
            if(R[j] != n && num[i] > num[R[j]]) bad++; // Check i vs new right neighbor

            // 5. Add new potential pairs to Heap
            if(L[i] != -1) pq.offer(new long[]{num[L[i]]+num[i], L[i]});
            if(R[i] != n) pq.offer(new long[]{num[i]+num[R[i]], i});

            op++;
        }

        return op;
    }
}