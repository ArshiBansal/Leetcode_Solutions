class Solution {
    public int minPartitions(String n) {
        int maxDigit=0;
        for(char digit:n.toCharArray()){
            maxDigit = Math.max(maxDigit, digit - '0'); // Convert char to integer
            if (maxDigit == 9) return 9; // Early exit since max is 9
        }
        return maxDigit;
    }
}