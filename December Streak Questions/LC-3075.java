import java.util.*;
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int picked=0,n=happiness.length;
        long sum=0;
        for(int i=n-1;i>=n-k;i--){
            long happyValue = Math.max((long)happiness[i] - picked, 0);
            sum += happyValue;
            picked++;
        }
        return sum;
    }
}