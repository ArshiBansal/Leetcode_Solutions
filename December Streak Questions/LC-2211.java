class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int left = 0, right = n - 1;

        // Skip all leading L's (they move left and never collide)
        while (left < n && directions.charAt(left) == 'L') {
            left++;
        }

        // Skip all trailing R's (they move right and never collide)
        while (right >= 0 && directions.charAt(right) == 'R') {
            right--;
        }

        // Now count collisions in the remaining segment
        int collisions = 0;
        for (int i = left; i <= right; i++) {
            char c = directions.charAt(i);
            if (c == 'L' || c == 'R') {
                collisions++;
            }
        }

        return collisions;
    }
}