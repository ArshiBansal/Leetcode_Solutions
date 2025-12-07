class Solution {
    public int countOdds(int low, int high) {
        int countHigh = (high + 1) / 2;
        int countLow = low / 2;
        return countHigh - countLow;
    }
}