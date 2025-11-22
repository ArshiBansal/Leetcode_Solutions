class Solution {
    public int numSub(String s) {
        long MOD = 1_000_000_007;
        long count = 0;  // current streak of consecutive 1's
        long result = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
                result = (result + count) % MOD;
            } else {
                count = 0;
            }
        }

        return (int) result;
    }
}