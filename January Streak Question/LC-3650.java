import java.util.*;

class Solution {

    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int minCost(int n, int[][] edges) {

        // Build graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Original + reversed edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph.get(u).add(new Edge(v, w));        // normal edge
            graph.get(v).add(new Edge(u, 2 * w));    // reversed via switch
        }

        // Dijkstra
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{0, 0}); // {node, cost}

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int node = (int) cur[0];
            long cost = cur[1];

            if (cost > dist[node]) continue;
            if (node == n - 1) return (int) cost;

            for (Edge e : graph.get(node)) {
                long newCost = cost + e.cost;
                if (newCost < dist[e.to]) {
                    dist[e.to] = newCost;
                    pq.offer(new long[]{e.to, newCost});
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}
