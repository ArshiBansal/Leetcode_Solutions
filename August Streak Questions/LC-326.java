class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 1) return true;       // base case
        if (n <= 0 || n % 3 != 0) return false;
        return isPowerOfThree(n / 3);  // recursive step
    }
}