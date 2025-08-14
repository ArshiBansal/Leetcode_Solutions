class Solution {
    public String largestGoodInteger(String num) {
        String maxGood = "";
        for (int i = 0; i <= num.length() - 3; i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
                String current = num.substring(i, i + 3);
                if (current.compareTo(maxGood) > 0) {
                    maxGood = current;
                }
            }
        }
        return maxGood;
    }
}
