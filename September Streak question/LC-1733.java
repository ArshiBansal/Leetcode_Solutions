import java.util.*;

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;

        // Convert languages[i] to a Set for fast lookup
        List<Set<Integer>> userLanguages = new ArrayList<>();
        for (int[] langs : languages) {
            Set<Integer> set = new HashSet<>();
            for (int lang : langs) {
                set.add(lang);
            }
            userLanguages.add(set);
        }

        // Step 1: Find users involved in problematic friendships
        Set<Integer> candidates = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1; // convert to 0-based
            int v = f[1] - 1;

            // Check if they share a common language
            boolean canCommunicate = false;
            for (int lang : userLanguages.get(u)) {
                if (userLanguages.get(v).contains(lang)) {
                    canCommunicate = true;
                    break;
                }
            }

            // If not, mark them as needing possible teaching
            if (!canCommunicate) {
                candidates.add(u);
                candidates.add(v);
            }
        }

        // If no problematic friendships, no teaching needed
        if (candidates.isEmpty()) return 0;

        // Step 2: Try teaching each language, count required teachings
        int minTeachings = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int user : candidates) {
                if (!userLanguages.get(user).contains(lang)) {
                    count++;
                }
            }
            minTeachings = Math.min(minTeachings, count);
        }

        return minTeachings;
    }
}
