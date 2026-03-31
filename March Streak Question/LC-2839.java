import java.util.Arrays;
class Solution {
    public boolean canBeEqual(String s1, String s2) {
        String group1_s1 = "" + s1.charAt(0) + s1.charAt(2);
        String group1_s2 = "" + s2.charAt(0) + s2.charAt(2);
        String group2_s1 = "" + s1.charAt(1) + s1.charAt(3);
        String group2_s2 = "" + s2.charAt(1) + s2.charAt(3);
        
        return (sorted(group1_s1).equals(sorted(group1_s2)) && sorted(group2_s1).equals(sorted(group2_s2)));
    }

    private String sorted(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
