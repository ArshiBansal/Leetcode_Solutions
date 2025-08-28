class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        // A number is a power of two if it has exactly one '1' bit in binary
        return (n & (n - 1)) == 0;
    }
}