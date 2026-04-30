import java.util.*;
class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> values=new ArrayList<>();
        for(int[] row:grid){
            for (int num : row) {
                values.add(num);
            }
        }
        int remainder=values.get(0) % x;
        for(int num:values){
            if (num % x != remainder) {
                return -1; // Not possible
            }
        }
        Collections.sort(values);
        int median = values.get(values.size() / 2);
        int operations=0;
        for(int num:values){
            operations += Math.abs(num - median) / x;
        }
        return operations;
    }
}