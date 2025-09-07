class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 60; k++) {
            long S = (long) num1 - (long) k * num2;
            if (S < k) continue; // not enough to distribute
            if (Long.bitCount(S) <= k && k <= S) {
                return k;
            }
        }
        return -1;
    }
}