import java.util.*;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxH = maxGap(hBars);
        int maxV = maxGap(vBars);
        
        int side = Math.min(maxH, maxV);
        return side * side;
    }

    private int maxGap(int[] bars) {
        Arrays.sort(bars);

        int maxRun = 1;
        int currRun = 1;

        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                currRun++;
            } else {
                maxRun = Math.max(maxRun, currRun);
                currRun = 1;
            }
        }
        maxRun = Math.max(maxRun, currRun);

        // Longest square side = longest consecutive bars removed + 1
        return maxRun + 1;
    }
}
