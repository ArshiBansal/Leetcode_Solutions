class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            int j = n - i;
            if (!containsZero(i) && !containsZero(j)) {
                return new int[] {i, j};
            }
        }
        return new int[] {0, 0}; // This case won't occur if input is valid.
    }

    private boolean containsZero(int num) {
        return String.valueOf(num).contains("0");
    }
}