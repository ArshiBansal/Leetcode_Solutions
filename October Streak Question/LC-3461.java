class Solution {
    public boolean hasSameDigits(String s) {
        int[] digits = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digits[i] = s.charAt(i) - '0';
        }

        // Repeat until only 2 digits remain
        while (digits.length > 2) {
            int[] next = new int[digits.length - 1];
            for (int i = 0; i < digits.length - 1; i++) {
                next[i] = (digits[i] + digits[i + 1]) % 10;
            }
            digits = next;
        }

        // Check if the final two digits are the same
        return digits[0] == digits[1];
    }
}