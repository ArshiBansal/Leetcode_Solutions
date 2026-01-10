class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;  // done, no carry
            }
            digits[i] = 0; // digit was 9, becomes 0 and carry continues
        }

        // If we reach here, all digits were 9 -> need an extra digit
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}