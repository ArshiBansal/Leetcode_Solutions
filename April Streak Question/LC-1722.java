import java.util.*;

class Solution {

    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        DSU dsu = new DSU(n);

        // Step 1: build connected components
        for (int[] sw : allowedSwaps) {
            dsu.union(sw[0], sw[1]);
        }

        // Step 2: root -> frequency map of source values
        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            groups
                .computeIfAbsent(root, k -> new HashMap<>())
                .merge(source[i], 1, Integer::sum);
        }

        int mismatch = 0;

        // Step 3: try to match target values within same component
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            Map<Integer, Integer> freq = groups.get(root);

            int val = target[i];

            if (freq != null && freq.getOrDefault(val, 0) > 0) {
                freq.put(val, freq.get(val) - 1);
            } else {
                mismatch++;
            }
        }

        return mismatch;
    }
}