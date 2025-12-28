import java.util.*;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // Sort meetings by time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        boolean[] knows = new boolean[n];
        knows[0] = true;
        knows[firstPerson] = true;

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];

            // Store people involved at this time
            Map<Integer, Integer> map = new HashMap<>();
            List<int[]> sameTimeMeetings = new ArrayList<>();

            // Collect all meetings with the same time
            while (i < meetings.length && meetings[i][2] == time) {
                sameTimeMeetings.add(meetings[i]);
                map.putIfAbsent(meetings[i][0], map.size());
                map.putIfAbsent(meetings[i][1], map.size());
                i++;
            }

            // Union-Find for current time
            int size = map.size();
            DSU dsu = new DSU(size);

            for (int[] m : sameTimeMeetings) {
                int x = map.get(m[0]);
                int y = map.get(m[1]);
                dsu.union(x, y);
            }

            // Check which components already know the secret
            Map<Integer, Boolean> hasSecret = new HashMap<>();
            for (int person : map.keySet()) {
                int root = dsu.find(map.get(person));
                hasSecret.putIfAbsent(root, false);
                if (knows[person]) {
                    hasSecret.put(root, true);
                }
            }

            // Spread the secret within components
            for (int person : map.keySet()) {
                int root = dsu.find(map.get(person));
                if (hasSecret.get(root)) {
                    knows[person] = true;
                }
            }
        }

        // Collect result
        List<Integer> result = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (knows[p]) {
                result.add(p);
            }
        }
        return result;
    }

    // Union-Find (Disjoint Set Union)
    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra != rb) {
                parent[rb] = ra;
            }
        }
    }
}
