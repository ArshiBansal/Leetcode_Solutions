class Solution {
    public int findClosest(int x, int y, int z) {
        int diff1 = Math.abs(z - x);
        int diff2 = Math.abs(z - y);
        
        if (diff1 < diff2) {
            return 1; // Person 1 is closer
        } else if (diff2 < diff1) {
            return 2; // Person 2 is closer
        } else {
            return 0; // Both are equidistant
        }
    }
}