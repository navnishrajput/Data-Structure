class Movie {
    String title;
    String director;
    int year;
    double rating;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("| Title: %-30s | Director: %-15s | Year: %-4d | Rating: %.1f |",
                title, director, year, rating);
    }
}

public class MovieManager {

    private class Node {
        Movie movie;
        Node prev;
        Node next;

        public Node(Movie movie) {
            this.movie = movie;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MovieManager() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // --- 1. Add Operations ---

    public void addFirst(Movie movie) {
        Node newNode = new Node(movie);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        System.out.println("Added: '" + movie.title + "' at the beginning.");
    }

    public void addLast(Movie movie) {
        Node newNode = new Node(movie);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        System.out.println("Added: '" + movie.title + "' at the end.");
    }

    public void addAtPosition(Movie movie, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position + ". List size is " + size + ".");
            return;
        }
        if (position == 1) {
            addFirst(movie);
            return;
        }
        if (position == size + 1) {
            addLast(movie);
            return;
        }

        Node newNode = new Node(movie);
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
        System.out.println("Added: '" + movie.title + "' at position " + position + ".");
    }

    // --- 2. Remove Operation ---

    public void removeByTitle(String title) {
        if (isEmpty()) {
            System.out.println("Cannot remove. The movie list is empty.");
            return;
        }

        Node current = head;
        while (current != null && !current.movie.title.equalsIgnoreCase(title)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Movie with title '" + title + "' not found.");
            return;
        }

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
        System.out.println("Removed: '" + title + "' successfully.");
    }

    // --- 3. Display Operations ---

    public void displayForward() {
        if (isEmpty()) {
            System.out.println("\n--- Movie Records (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Movie Records (Forward Order) [Size: " + size + "] ---");
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.movie);
            current = current.next;
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }

    public void displayReverse() {
        if (isEmpty()) {
            System.out.println("\n--- Movie Records (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Movie Records (Reverse Order) [Size: " + size + "] ---");
        Node current = tail;
        int count = size;
        while (current != null) {
            System.out.println(count-- + ". " + current.movie);
            current = current.prev;
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }

    // --- Main Method for Demonstration ---

    public static void main(String[] args) {
        MovieManager manager = new MovieManager();

        // Adding movies
        manager.addLast(new Movie("Inception", "Christopher Nolan", 2010, 8.8));
        manager.addLast(new Movie("Parasite", "Bong Joon-ho", 2019, 8.6));
        manager.addFirst(new Movie("The Matrix", "Wachowskis", 1999, 8.7));
        manager.addAtPosition(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9), 3); // Position 3

        manager.displayForward();
        manager.displayReverse();

        // Demonstration of removal
        System.out.println("\n--- DEMONSTRATION OF REMOVE OPERATION ---");

        // Remove a middle movie
        manager.removeByTitle("Pulp Fiction");

        // Remove the head movie
        manager.removeByTitle("The Matrix");

        // Remove a non-existent movie
        manager.removeByTitle("Titanic");

        manager.displayForward();
    }
}
