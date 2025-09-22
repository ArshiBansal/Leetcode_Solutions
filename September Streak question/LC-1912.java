import java.util.*;

class MovieRentingSystem {
    private static class Entry {
        int shop, movie, price;
        Entry(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
        // Needed so TreeSet can compare Entries correctly
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry)) return false;
            Entry other = (Entry) o;
            return shop == other.shop && movie == other.movie && price == other.price;
        }
        @Override
        public int hashCode() {
            return Objects.hash(shop, movie, price);
        }
    }

    // (shop, movie) -> price
    private Map<String, Integer> priceMap;

    // movie -> available unrented copies sorted by (price, shop)
    private Map<Integer, TreeSet<Entry>> available;

    // rented copies sorted globally by (price, shop, movie)
    private TreeSet<Entry> rented;

    public MovieRentingSystem(int n, int[][] entries) {
        priceMap = new HashMap<>();
        available = new HashMap<>();

        Comparator<Entry> movieComparator = (a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            return a.shop - b.shop;
        };

        Comparator<Entry> rentedComparator = (a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            if (a.shop != b.shop) return a.shop - b.shop;
            return a.movie - b.movie;
        };

        rented = new TreeSet<>(rentedComparator);

        // Initialize data
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            String key = shop + "#" + movie;
            priceMap.put(key, price);

            available.computeIfAbsent(movie, k -> new TreeSet<>(movieComparator));
            available.get(movie).add(new Entry(shop, movie, price));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        if (!available.containsKey(movie)) return result;
        Iterator<Entry> it = available.get(movie).iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            result.add(it.next().shop);
            count++;
        }
        return result;
    }

    public void rent(int shop, int movie) {
        String key = shop + "#" + movie;
        int price = priceMap.get(key);
        Entry e = new Entry(shop, movie, price);
        available.get(movie).remove(e);
        rented.add(e);
    }

    public void drop(int shop, int movie) {
        String key = shop + "#" + movie;
        int price = priceMap.get(key);
        Entry e = new Entry(shop, movie, price);
        rented.remove(e);
        available.computeIfAbsent(movie, k -> new TreeSet<>((a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            return a.shop - b.shop;
        }));
        available.get(movie).add(e);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        Iterator<Entry> it = rented.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            Entry e = it.next();
            result.add(Arrays.asList(e.shop, e.movie));
            count++;
        }
        return result;
    }
}
