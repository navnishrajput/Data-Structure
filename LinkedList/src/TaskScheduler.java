class Task {
    int id;
    String name;
    String priority;
    String dueDate;

    public Task(int id, String name, String priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("| ID: %-4d | Name: %-25s | Priority: %-8s | Due Date: %-10s |",
                id, name, priority, dueDate);
    }
}

public class TaskScheduler {

    private class Node {
        Task task;
        Node next;

        public Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public TaskScheduler() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // --- 1. Add Operations ---

    public void addFirst(Task task) {
        Node newNode = new Node(task);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Link back to itself
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head; // Maintain circularity
        }
        size++;
        System.out.println("Task Added: '" + task.name + "' at the beginning (ID: " + task.id + ").");
    }

    public void addLast(Task task) {
        if (isEmpty()) {
            addFirst(task);
            // addFirst increments size and prints message, so we return here.
            return;
        }

        Node newNode = new Node(task);
        tail.next = newNode;
        tail = newNode;
        tail.next = head; // Maintain circularity

        size++;
        System.out.println("Task Added: '" + task.name + "' at the end (ID: " + task.id + ").");
    }

    public void addAtPosition(Task task, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position + ". List size is " + size + ".");
            return;
        }
        if (position == 1) {
            addFirst(task);
            return;
        }
        if (position == size + 1) {
            addLast(task);
            return;
        }

        Node newNode = new Node(task);
        Node current = head;

        // Traverse to the node *before* the desired position
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        size++;
        System.out.println("Task Added: '" + task.name + "' at position " + position + " (ID: " + task.id + ").");
    }

    // --- 2. Remove Operation ---

    public void removeById(int taskId) {
        if (isEmpty()) {
            System.out.println("Cannot remove. The task list is empty.");
            return;
        }

        // Case 1: Removing the head
        if (head.task.id == taskId) {
            String removedName = head.task.name;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head; // Update tail's link to the new head
            }
            size--;
            System.out.println("Task Removed: '" + removedName + "' (ID: " + taskId + ") successfully.");
            return;
        }

        // Case 2: Removing a node in the middle or end
        Node current = head;
        // The loop condition ensures we don't check 'head' again and stop just before 'head'
        for (int i = 0; i < size - 1; i++) {
            if (current.next.task.id == taskId) {
                String removedName = current.next.task.name;

                // If the node to be removed is the tail
                if (current.next == tail) {
                    tail = current;
                }

                current.next = current.next.next;
                tail.next = head; // Ensure circularity is maintained even if tail changed

                size--;
                System.out.println("Task Removed: '" + removedName + "' (ID: " + taskId + ") successfully.");
                return;
            }
            current = current.next;
        }

        System.out.println("Task with ID " + taskId + " not found.");
    }

    // --- 3. Display Operation ---

    public void displayTasks() {
        if (isEmpty()) {
            System.out.println("\n--- Task Scheduler (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Task Scheduler (Circular List - Size: " + size + ") ---");
        Node current = head;
        int count = 1;

        // Traverse exactly 'size' times to avoid infinite loop
        for (int i = 0; i < size; i++) {
            System.out.println(count++ + ". " + current.task);
            current = current.next;
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }

    // --- Main Method for Demonstration ---

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        System.out.println("--- DEMONSTRATION OF ADD OPERATIONS ---");

        // Add at the end (using addLast, will use addFirst internally if empty)
        scheduler.addLast(new Task(100, "Review Code", "High", "2024-11-01"));
        scheduler.addLast(new Task(102, "Write Documentation", "Medium", "2024-11-05"));

        // Add at the beginning
        scheduler.addFirst(new Task(99, "Setup Project", "Critical", "2024-10-30"));

        // Add in the middle
        scheduler.addAtPosition(new Task(101, "Design Database Schema", "High", "2024-11-02"), 3);

        // Add new tail (position 5, size is currently 4)
        scheduler.addAtPosition(new Task(103, "Deployment Checklist", "Low", "2024-11-10"), 5);

        scheduler.displayTasks();

        System.out.println("\n--- DEMONSTRATION OF REMOVE OPERATION ---");

        // Remove the head (ID 99)
        scheduler.removeById(99);

        // Remove a middle task (ID 101)
        scheduler.removeById(101);

        // Try to remove a non-existent task
        scheduler.removeById(999);

        // Remove the new tail (ID 103)
        scheduler.removeById(103);

        scheduler.displayTasks();

        // Remove remaining tasks
        scheduler.removeById(100);
        scheduler.removeById(102);

        scheduler.displayTasks(); // Should show Empty List
    }
}
