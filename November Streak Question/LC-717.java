class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;

        while (i < n - 1) {    // stop before the last bit
            if (bits[i] == 1) {
                i += 2;       // skip a two-bit character
            } else {
                i += 1;       // skip a one-bit character
            }
        }

        // If we ended exactly at the last index, it's a one-bit character
        return i == n - 1;
    }
}