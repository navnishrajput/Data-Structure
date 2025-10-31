class Item {
    String name;
    int id;
    int quantity;
    double price;

    public Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("| ID: %-4d | Name: %-25s | Qty: %-5d | Price: $%-8.2f | Value: $%-10.2f |",
                id, name, quantity, price, quantity * price);
    }
}

public class InventoryManager {

    private class Node {
        Item item;
        Node next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public InventoryManager() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // --- 1. Add Operations ---

    public void addFirst(Item item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("Item Added: '" + item.name + "' (ID: " + item.id + ") at the beginning.");
    }

    public void addLast(Item item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Item Added: '" + item.name + "' (ID: " + item.id + ") at the end.");
    }

    public void addAtPosition(Item item, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position + ". Inventory size is " + size + ".");
            return;
        }
        if (position == 1) {
            addFirst(item);
            return;
        }
        if (position == size + 1) {
            addLast(item);
            return;
        }

        Node newNode = new Node(item);
        Node current = head;

        // Traverse to the node *before* the desired position
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Item Added: '" + item.name + "' (ID: " + item.id + ") at position " + position + ".");
    }

    // --- 2. Remove Operation ---

    public void removeItemById(int itemId) {
        if (isEmpty()) {
            System.out.println("Cannot remove. The inventory is empty.");
            return;
        }

        // Case 1: Deleting the head node
        if (head.item.id == itemId) {
            String removedName = head.item.name;
            head = head.next;
            size--;
            System.out.println("Item Removed: '" + removedName + "' (ID: " + itemId + ").");
            return;
        }

        // Case 2: Deleting a node in the middle or end
        Node current = head;
        // Search for the node *before* the one to be deleted
        while (current.next != null && current.next.item.id != itemId) {
            current = current.next;
        }

        // Check if the item was found (current.next is not null)
        if (current.next != null) {
            String removedName = current.next.item.name;
            // Bypass the node to be deleted: current.next skips to current.next.next
            current.next = current.next.next;
            size--;
            System.out.println("Item Removed: '" + removedName + "' (ID: " + itemId + ").");
        } else {
            System.out.println("Item with ID " + itemId + " not found.");
        }
    }

    // --- 3. Display and Calculate Total Value ---

    public void displayInventory() {
        if (isEmpty()) {
            System.out.println("\n--- Inventory Records (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Inventory Records (Size: " + size + ") ---");
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.item);
            current = current.next;
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public double calculateTotalInventoryValue() {
        double totalValue = 0.0;
        Node current = head;
        while (current != null) {
            totalValue += (current.item.price * current.item.quantity);
            current = current.next;
        }
        return totalValue;
    }

    // --- Main Method for Demonstration ---

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        System.out.println("--- DEMONSTRATION OF ADD OPERATIONS ---");

        manager.addLast(new Item("Laptop Charger", 201, 5, 25.50));
        manager.addFirst(new Item("Wireless Mouse", 100, 10, 15.00));
        manager.addAtPosition(new Item("External SSD", 300, 3, 85.99), 2);
        manager.addLast(new Item("Monitor Cable", 400, 20, 5.25));

        manager.displayInventory();

        double total = manager.calculateTotalInventoryValue();
        System.out.printf("Total Inventory Value: $%.2f\n", total);
        System.out.println("--------------------------------------------------------------------------\n");


        System.out.println("--- DEMONSTRATION OF REMOVE OPERATION ---");

        // Remove the head (ID 100)
        manager.removeItemById(100);

        // Remove a middle item (ID 300)
        manager.removeItemById(300);

        // Try to remove a non-existent item
        manager.removeItemById(999);

        manager.displayInventory();

        total = manager.calculateTotalInventoryValue();
        System.out.printf("Total Inventory Value after removal: $%.2f\n", total);
        System.out.println("--------------------------------------------------------------------------");
    }
}
