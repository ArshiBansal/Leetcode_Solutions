import java.util.*;

public class Solution {
    static final int MOD = 1_000_000_007;
    static long[] fact, invFact;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        // Check if any other computer has same complexity as 0
        int rootComp = complexity[0];
        for (int i = 1; i < n; i++) {
            if (complexity[i] == rootComp) return 0;
        }

        // Group computers by complexity
        TreeMap<Integer, Integer> compCount = new TreeMap<>();
        for (int c : complexity) compCount.put(c, compCount.getOrDefault(c, 0) + 1);

        // First group must contain computer 0
        if (compCount.firstKey() != rootComp) return 0;
        if (compCount.get(rootComp) == 0) return 0;

        // Precompute factorials for n
        precomputeFactorials(n);

        // Total ways:
        // multinomial coefficient for group sizes except first group, times factorials of group sizes
        long ways = 1;
        int remaining = n - 1; // exclude root computer fixed at first position

        boolean first = true;
        for (int groupSize : compCount.values()) {
            if (first) {
                // first group contains computer 0, we fix its position, so ways = groupSize! for remaining in group
                ways = (ways * fact[groupSize - 1]) % MOD;
                first = false;
            } else {
                ways = (ways * nCrMultinomial(remaining, groupSize)) % MOD;
                ways = (ways * fact[groupSize]) % MOD;
                remaining -= groupSize;
            }
        }

        return (int) ways;
    }

    // multinomial coefficient for picking k elements from n
    // here multinomial reduces to n choose k
    private long nCrMultinomial(int n, int k) {
        if (k > n || k < 0) return 0;
        return (fact[n] * ((invFact[k] * invFact[n - k]) % MOD)) % MOD;
    }

    private void precomputeFactorials(int n) {
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = (fact[i - 1] * i) % MOD;
        invFact[n] = modInverse(fact[n], MOD);
        for (int i = n - 1; i >= 0; i--) invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
    }

    private long modInverse(long a, int mod) {
        return pow(a, mod - 2, mod);
    }

    private long pow(long base, int exp, int mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
