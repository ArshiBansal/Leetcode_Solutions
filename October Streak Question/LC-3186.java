class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Long> damageMap = new HashMap<>();
        for (int p : power) {
            damageMap.put(p, damageMap.getOrDefault(p, 0L) + p);
        }

        List<Integer> unique = new ArrayList<>(damageMap.keySet());
        Collections.sort(unique);

        int n = unique.size();
        long[] dp = new long[n];
        dp[0] = damageMap.get(unique.get(0));

        for (int i = 1; i < n; i++) {
            long take = damageMap.get(unique.get(i));
            int j = i - 1;
            while (j >= 0 && unique.get(i) - unique.get(j) <= 2) j--;
            if (j >= 0) take += dp[j];
            long skip = dp[i - 1];
            dp[i] = Math.max(skip, take);
        }

        return dp[n - 1];
    }
}