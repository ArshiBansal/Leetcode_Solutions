import java.util.*;

class FoodRatings {
    private Map<String, String> foodToCuisine; // food -> cuisine
    private Map<String, Integer> foodToRating; // food -> rating
    private Map<String, TreeSet<String>> cuisineToFoods; // cuisine -> sorted foods

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            // Create TreeSet for this cuisine if not exists, with custom comparator
            cuisineToFoods.computeIfAbsent(cuisine, k -> new TreeSet<>((a, b) -> {
                int ra = foodToRating.get(a);
                int rb = foodToRating.get(b);
                if (ra != rb) return rb - ra; // higher rating first
                return a.compareTo(b); // lexicographically smaller first
            })).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> set = cuisineToFoods.get(cuisine);

        // Remove old version of food from TreeSet
        set.remove(food);
        // Update rating
        foodToRating.put(food, newRating);
        // Re-insert with new rating (TreeSet will reorder automatically)
        set.add(food);
    }

    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first(); // first() gives highest-rated due to comparator
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
