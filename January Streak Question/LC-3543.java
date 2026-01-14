class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0.0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        // Compute total area and search bounds
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            totalArea += (double) l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double target = totalArea / 2.0;

        // Binary search for minimum y where areaBelow >= target
        for (int iter = 0; iter < 80; iter++) { // enough for 1e-6 precision
            double mid = (low + high) / 2.0;
            double areaBelow = 0.0;

            for (int[] sq : squares) {
                int y = sq[1];
                int l = sq[2];

                if (mid <= y) {
                    continue;
                } else if (mid >= y + l) {
                    areaBelow += (double) l * l;
                } else {
                    areaBelow += (mid - y) * l;
                }
            }

            if (areaBelow >= target) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }
}