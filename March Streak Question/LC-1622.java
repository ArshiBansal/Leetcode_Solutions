import java.util.*;

class Fancy {
    private static final int MOD = 1_000_000_007;
    private List<Long> sequence;
    private long add;   // cumulative addition
    private long mult;  // cumulative multiplication

    public Fancy() {
        sequence = new ArrayList<>();
        add = 0;
        mult = 1;
    }
    
    public void append(int val) {
        // Store the value in "normalized" form
        long adjustedVal = ((val - add + MOD) % MOD * modInverse(mult, MOD)) % MOD;
        sequence.add(adjustedVal);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mult = (mult * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= sequence.size()) return -1;
        long val = sequence.get(idx);
        long result = (val * mult + add) % MOD;
        return (int) result;
    }
    
    // Modular inverse using Fermat's little theorem
    private long modInverse(long a, long mod) {
        return pow(a, mod - 2, mod);
    }
    
    // Fast exponentiation
    private long pow(long a, long b, long mod) {
        long result = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) result = (result * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return result;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */