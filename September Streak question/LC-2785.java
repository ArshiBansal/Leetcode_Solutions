class Solution {
    public String sortVowels(String s) {
        String vowels = "aeiouAEIOU";
        
        // Step 1: Collect vowels from s
        List<Character> vowelList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                vowelList.add(c);
            }
        }
        
        // Step 2: Sort vowels by ASCII order
        Collections.sort(vowelList);
        
        // Step 3: Rebuild string with vowels replaced in sorted order
        StringBuilder result = new StringBuilder();
        int vowelIndex = 0;
        
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                result.append(vowelList.get(vowelIndex++));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}