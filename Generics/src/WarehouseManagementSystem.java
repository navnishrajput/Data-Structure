import java.util.*;

abstract class WarehouseItem {
    protected String id;
    protected String name;
    protected double price;

    public WarehouseItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract String getItemType();

    @Override
    public String toString() {
        return String.format("%s[%s: %s - $%.2f]", getItemType(), id, name, price);
    }
}

class Electronics extends WarehouseItem {
    private int warrantyMonths;

    public Electronics(String id, String name, double price, int warrantyMonths) {
        super(id, name, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getItemType() {
        return "Electronics";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Warranty: %d months)", warrantyMonths);
    }
}

class Groceries extends WarehouseItem {
    private Date expiryDate;

    public Groceries(String id, String name, double price, Date expiryDate) {
        super(id, name, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getItemType() {
        return "Groceries";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Expires: %s)", expiryDate);
    }
}

class Furniture extends WarehouseItem {
    private String material;

    public Furniture(String id, String name, double price, String material) {
        super(id, name, price);
        this.material = material;
    }

    @Override
    public String getItemType() {
        return "Furniture";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Material: %s)", material);
    }
}

class Storage<T extends WarehouseItem> {
    private List<T> items;

    public Storage() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public T getItem(String id) {
        return items.stream()
                .filter(item -> item.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    public static void displayAllItems(List<? extends WarehouseItem> items) {
        System.out.println("All items in storage:");
        for (WarehouseItem item : items) {
            System.out.println("  - " + item);
        }
    }
}

public class WarehouseManagementSystem {
    public static void main(String[] args) {
        Storage<Electronics> electronicsStorage = new Storage<>();
        Storage<Groceries> groceriesStorage = new Storage<>();
        Storage<Furniture> furnitureStorage = new Storage<>();

        electronicsStorage.addItem(new Electronics("E001", "Laptop", 999.99, 24));
        electronicsStorage.addItem(new Electronics("E002", "Smartphone", 699.99, 12));

        groceriesStorage.addItem(new Groceries("G001", "Organic Milk", 4.99, new Date()));
        groceriesStorage.addItem(new Groceries("G002", "Whole Wheat Bread", 3.49, new Date()));

        furnitureStorage.addItem(new Furniture("F001", "Office Chair", 199.99, "Leather"));
        furnitureStorage.addItem(new Furniture("F002", "Desk", 299.99, "Wood"));

        List<WarehouseItem> allItems = new ArrayList<>();
        allItems.addAll(electronicsStorage.getAllItems());
        allItems.addAll(groceriesStorage.getAllItems());
        allItems.addAll(furnitureStorage.getAllItems());

        Storage.displayAllItems(allItems);

        System.out.println("\nSearching for item E001: " + electronicsStorage.getItem("E001"));
    }
}