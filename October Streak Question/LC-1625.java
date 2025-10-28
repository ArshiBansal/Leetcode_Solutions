class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        seen.add(s);
        
        String smallest = s;
        int n = s.length();

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.compareTo(smallest) < 0) {
                smallest = cur;
            }

            // Operation 1: Add 'a' to all odd indices
            char[] chars = cur.toCharArray();
            for (int i = 1; i < n; i += 2) {
                int newDigit = (chars[i] - '0' + a) % 10;
                chars[i] = (char) ('0' + newDigit);
            }
            String added = new String(chars);
            if (seen.add(added)) {
                queue.offer(added);
            }

            // Operation 2: Rotate right by b
            String rotated = cur.substring(n - b) + cur.substring(0, n - b);
            if (seen.add(rotated)) {
                queue.offer(rotated);
            }
        }

        return smallest;
    }
}