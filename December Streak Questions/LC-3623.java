import java.util.*;
    public int countTrapezoids(int[][] points) {
        final long MOD = 1_000_000_007L;

        // Count how many points exist at each y-coordinate
        Map<Integer, Integer> count = new HashMap<>();
        for (int[] p : points) {
            count.put(p[1], count.getOrDefault(p[1], 0) + 1);
        }

        // Compute h_y = C(cnt, 2) for each y with at least 2 points
        List<Long> hs = new ArrayList<>();
        for (int c : count.values()) {
            if (c >= 2) {
                long h = (long)c * (c - 1) / 2;
                hs.add(h);
            }
        }

        // Sort to safely accumulate prefix sums
        Collections.sort(hs);

        // Sum over all distinct pairs: sum(prefix * h[i])
        long total = 0;
        long prefix = 0;

        for (long h : hs) {
            total = (total + prefix * h) % MOD;
            prefix = (prefix + h) % MOD;
        }

        return (int)(total % MOD);
    }
}