import java.util.*;

interface ProductCategory {
    String getCategoryName();
    double getPriceRangeMin();
    double getPriceRangeMax();
}

class BookCategory implements ProductCategory {
    private String genre;

    public BookCategory(String genre) {
        this.genre = genre;
    }

    @Override
    public String getCategoryName() {
        return "Books - " + genre;
    }

    @Override
    public double getPriceRangeMin() {
        return 5.0;
    }

    @Override
    public double getPriceRangeMax() {
        return 100.0;
    }
}

class ClothingCategory implements ProductCategory {
    private String size;
    private String gender;

    public ClothingCategory(String size, String gender) {
        this.size = size;
        this.gender = gender;
    }

    @Override
    public String getCategoryName() {
        return "Clothing - " + gender + " (" + size + ")";
    }

    @Override
    public double getPriceRangeMin() {
        return 10.0;
    }

    @Override
    public double getPriceRangeMax() {
        return 500.0;
    }
}

class GadgetCategory implements ProductCategory {
    private String brand;

    public GadgetCategory(String brand) {
        this.brand = brand;
    }

    @Override
    public String getCategoryName() {
        return "Gadgets - " + brand;
    }

    @Override
    public double getPriceRangeMin() {
        return 50.0;
    }

    @Override
    public double getPriceRangeMax() {
        return 2000.0;
    }
}

class Product<T extends ProductCategory> {
    private String id;
    private String name;
    private double price;
    private T category;

    public Product(String id, String name, double price, T category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;

        if (price < category.getPriceRangeMin() || price > category.getPriceRangeMax()) {
            throw new IllegalArgumentException("Price out of range for category: " + category.getCategoryName());
        }
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public T getCategory() { return category; }

    public void setPrice(double price) {
        if (price < category.getPriceRangeMin() || price > category.getPriceRangeMax()) {
            throw new IllegalArgumentException("Price out of range for category: " + category.getCategoryName());
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product[%s: %s - $%.2f (%s)]", id, name, price, category.getCategoryName());
    }
}

class ProductCatalog {
    private List<? extends Product<?>> products;

    public ProductCatalog(List<? extends Product<?>> products) {
        this.products = new ArrayList<>(products);
    }

    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double currentPrice = product.getPrice();
        double discountedPrice = currentPrice * (1 - percentage / 100);
        product.setPrice(discountedPrice);
        System.out.printf("Applied %.1f%% discount to %s. New price: $%.2f\n",
                percentage, product.getName(), discountedPrice);
    }

    public void displayCatalog() {
        System.out.println("Product Catalog:");
        for (Product<?> product : products) {
            System.out.println("  - " + product);
        }
    }
}

public class OnlineMarketplace {
    public static void main(String[] args) {
        BookCategory fiction = new BookCategory("Fiction");
        ClothingCategory mensClothing = new ClothingCategory("M", "Men");
        GadgetCategory appleGadgets = new GadgetCategory("Apple");

        Product<BookCategory> book = new Product<>("B001", "The Great Novel", 25.99, fiction);
        Product<ClothingCategory> shirt = new Product<>("C001", "Cotton T-Shirt", 29.99, mensClothing);
        Product<GadgetCategory> phone = new Product<>("G001", "Smartphone", 999.99, appleGadgets);

        List<Product<?>> products = Arrays.asList(book, shirt, phone);
        ProductCatalog catalog = new ProductCatalog(products);

        catalog.displayCatalog();

        System.out.println("\nApplying discounts:");
        ProductCatalog.applyDiscount(book, 10.0);
        ProductCatalog.applyDiscount(shirt, 15.0);
        ProductCatalog.applyDiscount(phone, 5.0);

        System.out.println("\nUpdated catalog:");
        catalog.displayCatalog();
    }
}