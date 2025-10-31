class Book {
    String title;
    String author;
    String genre;
    int id;
    boolean isAvailable;

    public Book(String title, String author, String genre, int id, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.isAvailable = isAvailable;
    }

    public String getStatus() {
        return isAvailable ? "Available" : "Checked Out";
    }

    @Override
    public String toString() {
        return String.format("| ID: %-4d | Title: %-30s | Author: %-20s | Genre: %-10s | Status: %-11s |",
                id, title, author, genre, getStatus());
    }
}

public class LibraryManager {

    private class Node {
        Book book;
        Node prev;
        Node next;

        public Node(Book book) {
            this.book = book;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LibraryManager() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // --- 1. Add Operations ---

    public void addFirst(Book book) {
        Node newNode = new Node(book);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        System.out.println("Book Added: '" + book.title + "' (ID: " + book.id + ") at the beginning.");
    }

    public void addLast(Book book) {
        Node newNode = new Node(book);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        System.out.println("Book Added: '" + book.title + "' (ID: " + book.id + ") at the end.");
    }

    public void addAtPosition(Book book, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position + ". List size is " + size + ".");
            return;
        }
        if (position == 1) {
            addFirst(book);
            return;
        }
        if (position == size + 1) {
            addLast(book);
            return;
        }

        Node newNode = new Node(book);
        Node current = head;

        // Traverse to the node *before* the desired position
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;

        size++;
        System.out.println("Book Added: '" + book.title + "' (ID: " + book.id + ") at position " + position + ".");
    }

    // --- 2. Remove Operation ---

    public void removeById(int bookId) {
        if (isEmpty()) {
            System.out.println("Cannot remove. The library list is empty.");
            return;
        }

        Node current = head;
        while (current != null && current.book.id != bookId) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        String removedTitle = current.book.title;

        // Case 1: Node is the head
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; // List becomes empty
            }
        }
        // Case 2: Node is the tail
        else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        }
        // Case 3: Node is in the middle
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        System.out.println("Book Removed: '" + removedTitle + "' (ID: " + bookId + ") successfully.");
    }

    // --- 3. Display Operations ---

    public void displayForward() {
        if (isEmpty()) {
            System.out.println("\n--- Library Records (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Library Records (Forward Order) [Total Books: " + size + "] ---");
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.book);
            current = current.next;
        }
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }

    public void displayReverse() {
        if (isEmpty()) {
            System.out.println("\n--- Library Records (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Library Records (Reverse Order) [Total Books: " + size + "] ---");
        Node current = tail;
        int count = size;
        while (current != null) {
            System.out.println(count-- + ". " + current.book);
            current = current.prev;
        }
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }

    // --- Main Method for Demonstration ---

    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();

        System.out.println("--- DEMONSTRATION OF ADD OPERATIONS ---");

        // Add at the end
        manager.addLast(new Book("Dune", "Frank Herbert", "Sci-Fi", 501, true));
        manager.addLast(new Book("1984", "George Orwell", "Dystopian", 503, false));

        // Add at the beginning
        manager.addFirst(new Book("Pride and Prejudice", "Jane Austen", "Romance", 500, true));

        // Add at a specific position (2nd position)
        manager.addAtPosition(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 502, true), 3);

        manager.displayForward();

        System.out.println("\n--- DEMONSTRATION OF REVERSE DISPLAY ---");
        manager.displayReverse();

        System.out.println("\n--- DEMONSTRATION OF REMOVE OPERATION ---");

        // Remove a middle book (ID 502)
        manager.removeById(502);

        // Remove the head book (ID 500)
        manager.removeById(500);

        // Try to remove a non-existent book
        manager.removeById(999);

        manager.displayForward();
        manager.displayReverse();
    }
}
