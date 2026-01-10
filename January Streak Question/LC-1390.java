class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            int sum = 0;
            int count = 0;

            // Iterate only up to sqrt(num)
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    int div1 = i;
                    int div2 = num / i;

                    // Count div1
                    sum += div1;
                    count++;

                    // Count div2 if it's different
                    if (div1 != div2) {
                        sum += div2;
                        count++;
                    }

                    // If more than 4 divisors, no need to continue
                    if (count > 4) {
                        sum = 0;
                        break;
                    }
                }
            }

            // Only add if it has exactly 4 divisors
            if (count == 4) {
                totalSum += sum;
            }
        }

        return totalSum;
    }
}