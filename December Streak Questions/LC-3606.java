import java.util.*;

class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

        // Business line priority order
        List<String> order = Arrays.asList(
                "electronics",
                "grocery",
                "pharmacy",
                "restaurant"
        );

        // Map to store valid coupons by business line
        Map<String, List<String>> map = new HashMap<>();
        for (String bl : order) {
            map.put(bl, new ArrayList<>());
        }

        // Regex to validate coupon code
        String codeRegex = "^[A-Za-z0-9_]+$";

        // Validate coupons
        for (int i = 0; i < code.length; i++) {

            // Must be active
            if (!isActive[i]) continue;

            // Code must be non-empty and valid
            if (code[i] == null || code[i].isEmpty()) continue;
            if (!code[i].matches(codeRegex)) continue;

            // Business line must be valid
            if (!map.containsKey(businessLine[i])) continue;

            map.get(businessLine[i]).add(code[i]);
        }

        // Build result list in required order
        List<String> result = new ArrayList<>();
        for (String bl : order) {
            List<String> codes = map.get(bl);
            Collections.sort(codes);
            result.addAll(codes);
        }

        return result;
    }
}
