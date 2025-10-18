class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int max = Integer.MIN_VALUE;
        for (int i=energy.length-k;i<energy.length;i++) {
            max = Math.max(max, energy[i]);
        }
        
        for (int i=energy.length-k-1;i>=0;i--) {
            energy[i] += energy[i+k];
            max = Math.max(max, energy[i]);
        }
        return max;
        
    }
}