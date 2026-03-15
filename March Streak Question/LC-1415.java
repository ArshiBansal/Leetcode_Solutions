class Solution {
    private int count = 0;
    private String result = "";
    
    public String getHappyString(int n, int k) {
        backtrack(new StringBuilder(), n, k, ' '); // Start with no previous character
        return result;
    }
    
    private void backtrack(StringBuilder sb, int n, int k, char prev) {
        // Stop if result is already found
        if (!result.isEmpty()) return;
        
        if (sb.length() == n) {
            count++;
            if (count == k) {
                result = sb.toString();
            }
            return;
        }
        
        // Try 'a', 'b', 'c' in lexicographical order
        for (char c : new char[]{'a', 'b', 'c'}) {
            if (c != prev) {
                sb.append(c);
                backtrack(sb, n, k, c);
                sb.deleteCharAt(sb.length() - 1); // backtrack
            }
        }
    }
}
