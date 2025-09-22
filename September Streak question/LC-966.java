import java.util.*;

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>();
        Map<String, String> caseInsensitiveMap = new HashMap<>();
        Map<String, String> vowelErrorMap = new HashMap<>();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));

        // Preprocessing
        for (String word : wordlist) {
            exactWords.add(word);

            String lower = word.toLowerCase();
            caseInsensitiveMap.putIfAbsent(lower, word);

            String devoweled = devowel(lower, vowels);
            vowelErrorMap.putIfAbsent(devoweled, word);
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            // 1. Exact match
            if (exactWords.contains(query)) {
                result[i] = query;
                continue;
            }

            String lower = query.toLowerCase();

            // 2. Case-insensitive match
            if (caseInsensitiveMap.containsKey(lower)) {
                result[i] = caseInsensitiveMap.get(lower);
                continue;
            }

            // 3. Vowel error match
            String devoweled = devowel(lower, vowels);
            if (vowelErrorMap.containsKey(devoweled)) {
                result[i] = vowelErrorMap.get(devoweled);
            } else {
                result[i] = "";
            }
        }
        return result;
    }

    private String devowel(String word, Set<Character> vowels) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (vowels.contains(c)) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
