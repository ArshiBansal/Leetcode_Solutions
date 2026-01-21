class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;

        // Prefix sum matrix
        int[][] ps = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                ps[i][j] = mat[i - 1][j - 1]
                         + ps[i - 1][j]
                         + ps[i][j - 1]
                         - ps[i - 1][j - 1];
            }
        }

        int left = 0, right = Math.min(m, n);

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (existsSquare(ps, mid, threshold)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean existsSquare(int[][] ps, int len, int threshold) {
        for (int i = len; i < ps.length; i++) {
            for (int j = len; j < ps[0].length; j++) {
                int sum = ps[i][j]
                        - ps[i - len][j]
                        - ps[i][j - len]
                        + ps[i - len][j - len];
                if (sum <= threshold) return true;
            }
        }
        return false;
    }
}
