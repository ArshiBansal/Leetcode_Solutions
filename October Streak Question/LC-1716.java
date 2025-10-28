public class Solution {
    public int totalMoney(int n) {
        int fullWeeks = n / 7; // Number of complete weeks
        int remainingDays = n % 7; 
        int total = fullWeeks * 28 + (fullWeeks * (fullWeeks - 1) / 2) * 7;
        total += (fullWeeks+1)*remainingDays+(remainingDays*(remainingDays-1)/2);
        
        return total;
    }
}