class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int diff1 = 0, diff2 = 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < 2 * n; i++) {
            char c = s.charAt(i % n);

            if (c != (i % 2 == 0 ? '0' : '1')) diff1++;
            if (c != (i % 2 == 0 ? '1' : '0')) diff2++;

            if (i >= n) {
                char left = s.charAt((i - n) % n);

                if (left != ((i - n) % 2 == 0 ? '0' : '1')) diff1--;
                if (left != ((i - n) % 2 == 0 ? '1' : '0')) diff2--;
            }

            if (i >= n - 1) {
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }

        return res;
    }
}