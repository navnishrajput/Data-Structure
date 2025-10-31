class Ticket {
    int id;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;

    public Ticket(int id, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.id = id;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return String.format("| ID: %-4d | Customer: %-20s | Movie: %-20s | Seat: %-6s | Time: %-15s |",
                id, customerName, movieName, seatNumber, bookingTime);
    }
}

public class TicketReservationSystem {

    private class Node {
        Ticket ticket;
        Node next;

        public Node(Ticket ticket) {
            this.ticket = ticket;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public TicketReservationSystem() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // --- 1. Add Operation (Add at the End) ---

    public void addReservation(Ticket ticket) {
        Node newNode = new Node(ticket);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Link back to itself
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Maintain circularity
        }

        size++;
        System.out.println("Reservation Added: '" + ticket.customerName + "' for movie '" + ticket.movieName + "'. (ID: " + ticket.id + ")");
    }

    // --- 2. Remove Operation ---

    public void removeById(int ticketId) {
        if (isEmpty()) {
            System.out.println("Cannot cancel reservation. The ticket list is empty.");
            return;
        }

        // Case 1: Removing the head
        if (head.ticket.id == ticketId) {
            String removedName = head.ticket.customerName;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head; // Update tail's link to the new head
            }
            size--;
            System.out.println("Reservation Cancelled: " + removedName + " (ID: " + ticketId + ") successfully.");
            return;
        }

        // Case 2: Removing a node in the middle or end
        Node current = head;
        // Iterate up to the node before the tail to check for the ID
        for (int i = 0; i < size - 1; i++) {
            if (current.next.ticket.id == ticketId) {
                String removedName = current.next.ticket.customerName;

                // If the node to be removed is the tail
                if (current.next == tail) {
                    tail = current;
                }

                current.next = current.next.next;
                tail.next = head; // Ensure circularity is maintained even if tail changed

                size--;
                System.out.println("Reservation Cancelled: " + removedName + " (ID: " + ticketId + ") successfully.");
                return;
            }
            current = current.next;
        }

        System.out.println("Ticket with ID " + ticketId + " not found.");
    }

    // --- 3. Display Operation ---

    public void displayTickets() {
        if (isEmpty()) {
            System.out.println("\n--- Current Ticket Reservations (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Current Ticket Reservations (Total: " + size + ") ---");
        Node current = head;
        int count = 1;

        // Traverse exactly 'size' times to avoid infinite loop
        for (int i = 0; i < size; i++) {
            System.out.println(count++ + ". " + current.ticket);
            current = current.next;
        }
        System.out.println("--------------------------------------------------------------------------------------\n");
    }

    // --- 4. Calculate Total Tickets ---

    public int getTotalTickets() {
        return size;
    }


    // --- Main Method for Demonstration ---

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        System.out.println("--- DEMONSTRATION OF ADD OPERATIONS ---");

        system.addReservation(new Ticket(1001, "Sarah Connor", "The Matrix", "A10", "18:00"));
        system.addReservation(new Ticket(1002, "John Doe", "Inception", "B05", "20:30"));
        system.addReservation(new Ticket(1003, "Jane Smith", "Dune", "C01", "18:00"));

        system.displayTickets();
        System.out.println("Total booked tickets: " + system.getTotalTickets());

        System.out.println("\n--- DEMONSTRATION OF REMOVE OPERATION ---");

        // Remove a middle ticket (ID 1002)
        system.removeById(1002);

        // Remove the head (ID 1001)
        system.removeById(1001);

        // Try to remove a non-existent task
        system.removeById(9999);

        system.displayTickets();
        System.out.println("Total booked tickets: " + system.getTotalTickets());

        // Remove the last remaining ticket
        system.removeById(1003);

        system.displayTickets();
        System.out.println("Total booked tickets: " + system.getTotalTickets());
    }
}
