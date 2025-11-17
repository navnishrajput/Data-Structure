import java.util.*;

class Product {
    private String productId;
    private String productName;
    private double price;

    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public void displayProductInfo() {
        System.out.println("Product: " + productName + " (ID: " + productId + ") - $" + price);
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private List<Product> products;
    private double totalAmount;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalAmount += product.getPrice();
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Products in order:");
        for (Product product : products) {
            product.displayProductInfo();
        }
        System.out.println("Total Amount: $" + totalAmount);
    }
}

class Customer {
    private String customerId;
    private String name;
    private String email;
    private List<Order> orders;

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Order placeOrder(String orderId) {
        Order order = new Order(orderId, this);
        orders.add(order);
        System.out.println(name + " placed a new order: " + orderId);
        return order;
    }

    public void displayCustomerOrders() {
        System.out.println("Orders for " + name + ":");
        for (Order order : orders) {
            order.displayOrderDetails();
            System.out.println();
        }
    }
}

public class EcommercePlatform {
    public static void main(String[] args) {
        Customer customer1 = new Customer("C001", "Alice Johnson", "alice@email.com");
        Customer customer2 = new Customer("C002", "Bob Smith", "bob@email.com");

        Product laptop = new Product("P001", "Laptop", 999.99);
        Product phone = new Product("P002", "Smartphone", 699.99);
        Product headphones = new Product("P003", "Headphones", 149.99);

        Order order1 = customer1.placeOrder("ORD001");
        order1.addProduct(laptop);
        order1.addProduct(headphones);

        Order order2 = customer2.placeOrder("ORD002");
        order2.addProduct(phone);
        order2.addProduct(headphones);

        Order order3 = customer1.placeOrder("ORD003");
        order3.addProduct(laptop);
        order3.addProduct(phone);

        System.out.println();
        customer1.displayCustomerOrders();
        customer2.displayCustomerOrders();
    }
}