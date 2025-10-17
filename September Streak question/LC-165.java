class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        int length = Math.max(v1Parts.length, v2Parts.length);

        for (int i = 0; i < length; i++) {
            // Parse revision parts, treating missing ones as 0
            int num1 = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
            int num2 = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;

            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
        }
        return 0;
    }
}
