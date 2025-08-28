class Solution {
    public int maximum69Number (int num) {
        char[] digits = String.valueOf(num).toCharArray();
        
        // Replace the first '6' with '9'
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break; // only one change allowed
            }
        }
        
        // Convert back to integer
        return Integer.parseInt(new String(digits));
    }
}