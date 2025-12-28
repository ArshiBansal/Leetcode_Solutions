class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();

        // Initial penalty: close at hour 0
        // All 'Y' hours are closed with customers
        int penalty = 0;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                penalty++;
            }
        }

        int minPenalty = penalty;
        int bestHour = 0;

        // Try closing at hour j = 1 to n
        for (int j = 1; j <= n; j++) {
            char prevHour = customers.charAt(j - 1);

            if (prevHour == 'Y') {
                // One less closed hour with customers
                penalty--;
            } else {
                // One more open hour with no customers
                penalty++;
            }

            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestHour = j;
            }
        }

        return bestHour;
    }
}