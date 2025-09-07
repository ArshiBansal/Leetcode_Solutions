class Solution {
    public int[] sumZero(int n) {
        if (n == 1) return new int[] {0};
        
        int[] result = new int[n];
        
        for (int i = 0; i < n / 2; i++) {
            result[i] = i + 1;
            result[n - 1 - i] = -(i + 1);
        }
        
        if (n % 2 != 0) {
            result[n / 2] = 0;
        }
        
        return result;
    }
}