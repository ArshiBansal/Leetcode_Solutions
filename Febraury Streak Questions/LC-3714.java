import java.util.*;

class Solution {
    public int longestBalanced(String s) {
        HashMap<Long, Long> ab = new HashMap<>();
        HashMap<Long, Long> bc = new HashMap<>();
        HashMap<Long, Long> ca = new HashMap<>();
        HashMap<Long, Long> abc = new HashMap<>();

        long cnta = 0, cntb = 0, cntc = 0;
        long longestLength = 1;
        ab.put(0L, 0L);
        bc.put(0L, 0L);
        ca.put(0L, 0L);
        abc.put(0L, 0L);
        char currentChar = s.charAt(0);
        long len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (currentChar == s.charAt(i)) len++;
            else { len = 1; currentChar = s.charAt(i); }
            longestLength = Math.max(longestLength, len);
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') cnta++;
            else if (ch == 'b') cntb++;
            else cntc++;

            long abId  = ((cntb - cnta) << 32) | (cntc & 0xFFFFFFFFL);
            long bcId  = ((cntb - cntc) << 32) | (cnta & 0xFFFFFFFFL);
            long caId  = ((cntc - cnta) << 32) | (cntb & 0xFFFFFFFFL);
            long abcId = ((cntb - cnta) << 32) | ((cntc - cnta) & 0xFFFFFFFFL);

            if (!ab.containsKey(abId)) ab.put(abId, (long)i + 1);
            else longestLength = Math.max(longestLength, i - ab.get(abId) + 1);

            if (!bc.containsKey(bcId)) bc.put(bcId, (long)i + 1);
            else longestLength = Math.max(longestLength, i - bc.get(bcId) + 1);

            if (!ca.containsKey(caId)) ca.put(caId, (long)i + 1);
            else longestLength = Math.max(longestLength, i - ca.get(caId) + 1);

            if (!abc.containsKey(abcId)) abc.put(abcId, (long)i + 1);
            else longestLength = Math.max(longestLength, i - abc.get(abcId) + 1);
        }
        return (int)longestLength;
    }
}