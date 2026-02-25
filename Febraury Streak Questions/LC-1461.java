import java.util.HashSet;
import java.util.Set;
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n < k) return false; 
        Set<Integer> seen = new HashSet<>();
        int allOnes = (1 << k) - 1; 
        int hash = 0;
        
        for (int i = 0; i < n; i++) {
            hash = ((hash << 1) & allOnes) | (s.charAt(i) - '0');
            if (i >= k - 1) {
                seen.add(hash);
            }
        }
        return seen.size() == (1 << k);
    }
}