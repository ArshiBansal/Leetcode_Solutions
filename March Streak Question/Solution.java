public class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;

        char[] res = new char[len];
        boolean[] locked = new boolean[len];

        // Step 1: fill with '?'
        for (int i = 0; i < len; i++) {
            res[i] = '?';
        }

        // Step 2: enforce 'T' and lock positions
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] == '?' || res[i + j] == str2.charAt(j)) {
                        res[i + j] = str2.charAt(j);
                        locked[i + j] = true;
                    } else {
                        return "";
                    }
                }
            }
        }

        // Step 3: fill remaining with 'a'
        for (int i = 0; i < len; i++) {
            if (res[i] == '?') {
                res[i] = 'a';
            }
        }

        // Step 4: enforce 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                if (matches(res, i, str2)) {
                    boolean changed = false;

                    // try to break match
                    for (int j = m - 1; j >= 0; j--) {
                        int pos = i + j;

                        // ❗ skip locked positions
                        if (locked[pos]) continue;

                        char original = res[pos];

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != original) {
                                res[pos] = c;

                                if (!matches(res, i, str2)) {
                                    changed = true;
                                    break;
                                }
                            }
                        }

                        if (changed) break;
                        res[pos] = original;
                    }

                    if (!changed) return "";
                }
            }
        }

        return new String(res);
    }

    private boolean matches(char[] res, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (res[start + j] != str2.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}