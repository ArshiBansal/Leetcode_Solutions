class Solution {
    public int countTriples(int n) {
        int count = 0;
        for (int c = 1; c <= n; c++) {
            for (int a = 1; a < c; a++) {
                for (int b = 1; b < c; b++) {
                    if (a * a + b * b == c * c) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
