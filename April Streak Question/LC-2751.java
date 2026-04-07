import java.util.*;

class Solution {
    static class Robot {
        int pos, health, index;
        char dir;

        Robot(int p, int h, char d, int i) {
            pos = p;
            health = h;
            dir = d;
            index = i;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;

        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], healths[i], directions.charAt(i), i);
        }

        Arrays.sort(robots, (a, b) -> a.pos - b.pos);

        Stack<Robot> stack = new Stack<>(); // ONLY for 'R'

        for (Robot curr : robots) {
            if (curr.dir == 'R') {
                stack.push(curr);
            } else {
                // 'L' robot
                while (!stack.isEmpty() && curr.health > 0) {
                    Robot top = stack.peek();

                    if (top.health < curr.health) {
                        stack.pop();
                        curr.health--;
                    } else if (top.health > curr.health) {
                        top.health--;
                        curr.health = 0;
                    } else {
                        stack.pop();
                        curr.health = 0;
                    }
                }
                // IMPORTANT: do NOT push L into stack
            }
        }

        // Collect survivors
        List<Robot> survivors = new ArrayList<>();

        // Remaining R robots
        survivors.addAll(stack);

        // Also include L robots that survived
        for (Robot r : robots) {
            if (r.dir == 'L' && r.health > 0) {
                survivors.add(r);
            }
        }

        // Sort by original index
        Collections.sort(survivors, (a, b) -> a.index - b.index);

        List<Integer> result = new ArrayList<>();
        for (Robot r : survivors) {
            result.add(r.health);
        }

        return result;
    }
}