import java.util.*;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        
        for (int num : nums) {
            stack.add(num);
            // Keep merging as long as the last two elements are non-coprime
            while (stack.size() >= 2) {
                int last = stack.get(stack.size() - 1);
                int secondLast = stack.get(stack.size() - 2);
                int gcd = gcd(last, secondLast);
                
                if (gcd == 1) break; // coprime, stop merging
                
                // Merge the two numbers into their LCM
                long lcm = lcm(last, secondLast, gcd);
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add((int) lcm);
            }
        }
        
        return stack;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    private long lcm(int a, int b, int gcd) {
        return (long) a / gcd * b; // prevent overflow using long
    }
}
