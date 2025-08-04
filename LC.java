import java.util.HashMap;
import java.util.Map;

public class LC(904){
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to the basket
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If more than 2 types of fruits, shrink the window from the left
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            // Update maxFruits with the current window size
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}
