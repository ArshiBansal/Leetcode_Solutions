class Solution {
    public boolean reorderedPowerOf2(int n) {
        int[] targetCount = digitCount(n);

        // Check all powers of two up to 1 billion
        for (int i = 0; i < 31; i++) {
            int power = 1 << i;  // 2^i
            if (java.util.Arrays.equals(targetCount, digitCount(power))) {
                return true;
            }
        }
        return false;
    }

    // Count the frequency of each digit
    private int[] digitCount(int num) {
        int[] count = new int[10];
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }
        return count;
    }
}
