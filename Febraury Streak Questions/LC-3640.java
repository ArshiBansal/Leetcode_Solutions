class Solution {
    public long maxSumTrionic(int[] num) {
        int n = num.length;
        long neg = Long.MIN_VALUE / 4;

        long increase = neg, decrease = neg, increase2 = neg, ans = neg;

        for (int i = 1; i < n; i++) {
            long inc = neg, dec = neg, inc2 = neg;

            if (num[i] > num[i - 1]) {
                inc = Math.max(num[i - 1] + num[i], increase + num[i]);
                inc2 = Math.max(increase2 + num[i], decrease + num[i]);
            }

            if (num[i] < num[i - 1]) {
                dec = Math.max(decrease + num[i], increase + num[i]);
            }

            increase = inc;
            decrease = dec;
            increase2 = inc2;
            ans = Math.max(ans, inc2);
        }

        return ans;
    }
}
