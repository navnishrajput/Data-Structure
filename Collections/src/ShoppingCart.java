import java.util.*;

public class ShoppingCart {
    private Map<String, Double> productPrices;
    private Map<String, Integer> cart;
    private Map<String, Integer> sortedCart;

    public ShoppingCart() {
        productPrices = new HashMap<>();
        cart = new LinkedHashMap<>();
        sortedCart = new TreeMap<>();

        productPrices.put("Laptop", 999.99);
        productPrices.put("Phone", 699.99);
        productPrices.put("Headphones", 149.99);
        productPrices.put("Tablet", 399.99);
    }

    public void addToCart(String product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        sortedCart.put(product, sortedCart.getOrDefault(product, 0) + quantity);
    }

    public void displayCart() {
        System.out.println("Cart (insertion order):");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            System.out.printf("%s: %d x $%.2f = $%.2f\n",
                    entry.getKey(), entry.getValue(), productPrices.get(entry.getKey()),
                    entry.getValue() * productPrices.get(entry.getKey()));
        }

        System.out.println("\nCart (sorted by product name):");
        for (Map.Entry<String, Integer> entry : sortedCart.entrySet()) {
            System.out.printf("%s: %d x $%.2f = $%.2f\n",
                    entry.getKey(), entry.getValue(), productPrices.get(entry.getKey()),
                    entry.getValue() * productPrices.get(entry.getKey()));
        }

        System.out.println("\nProducts sorted by price:");
        productPrices.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.printf("%s: $%.2f\n", entry.getKey(), entry.getValue()));
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            total += entry.getValue() * productPrices.get(entry.getKey());
        }
        return total;
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addToCart("Laptop", 1);
        cart.addToCart("Phone", 2);
        cart.addToCart("Headphones", 1);
        cart.addToCart("Tablet", 1);

        cart.displayCart();
        System.out.printf("\nTotal: $%.2f\n", cart.getTotal());
    }
}