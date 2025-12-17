class Solution {
    public int numberOfWays(String corridor) {
        final long MOD = 1_000_000_007;
        long ways = 1;

        int seatCount = 0;
        int plantCount = 0;
        boolean countingPlants = false;

        for (int i = 0; i < corridor.length(); i++) {
            char c = corridor.charAt(i);

            if (c == 'S') {
                seatCount++;

                // When we reach the first seat of a new pair
                if (seatCount % 2 == 1 && seatCount > 1) {
                    ways = (ways * (plantCount + 1)) % MOD;
                    plantCount = 0;
                }

                countingPlants = (seatCount % 2 == 0);
            } else if (countingPlants) {
                plantCount++;
            }
        }

        // Total seats must be even and at least 2
        if (seatCount < 2 || seatCount % 2 != 0) {
            return 0;
        }

        return (int) ways;
    }
}