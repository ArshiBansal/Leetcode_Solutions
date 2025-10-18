import java.util.*;

class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (!result.isEmpty() && isAnagram(result.get(result.size() - 1), word)) {
                // Skip current word since it's an anagram of the previous one
                continue;
            } else {
                result.add(word);
            }
        }

        return result;
    }

    private boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int[] freq = new int[26];
        for (char c : s1.toCharArray()) freq[c - 'a']++;
        for (char c : s2.toCharArray()) freq[c - 'a']--;

        for (int count : freq)
            if (count != 0)
                return false;

        return true;
    }
}
