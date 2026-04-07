class Robot {

    int w, h;
    int x, y;
    int dir; // 0=East, 1=North, 2=West, 3=South
    int perimeter;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // East
        this.perimeter = 2 * (width + height) - 4;
    }

    public void step(int num) {
        num %= perimeter;

        // Special case: full cycle
        if (num == 0) {
            if (x == 0 && y == 0) {
                dir = 3; // South
            }
            return;
        }

        while (num > 0) {
            int steps = 0;

            if (dir == 0) { // East
                steps = Math.min(num, w - 1 - x);
                x += steps;
            } else if (dir == 1) { // North
                steps = Math.min(num, h - 1 - y);
                y += steps;
            } else if (dir == 2) { // West
                steps = Math.min(num, x);
                x -= steps;
            } else { // South
                steps = Math.min(num, y);
                y -= steps;
            }

            num -= steps;

            if (num > 0) {
                dir = (dir + 1) % 4; // turn counterclockwise
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        String[] dirs = {"East", "North", "West", "South"};
        return dirs[dir];
    }
}