class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int lo = 0, hi = 200000;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (canBuild(n, edges, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean canBuild(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        int used = 0;
        int upgrades = 0;

        // mandatory edges
        for (int[] e : edges) {
            if (e[3] == 1) {
                int u = e[0], v = e[1], s = e[2];

                if (s < target) return false;

                if (!dsu.union(u, v)) return false;
                used++;
            }
        }

        // optional edges without upgrade
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= target) {
                if (dsu.union(e[0], e[1])) {
                    used++;
                }
            }
        }

        // optional edges with upgrade
        for (int[] e : edges) {
            if (used == n - 1) break;

            if (e[3] == 0 && e[2] < target && e[2] * 2 >= target) {
                if (dsu.union(e[0], e[1])) {
                    upgrades++;
                    used++;
                    if (upgrades > k) return false;
                }
            }
        }

        return used == n - 1;
    }
}