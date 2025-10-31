class Student {
    int rollNumber;
    String name;
    int age;
    String grade;

    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("| Roll No: %-4d | Name: %-15s | Age: %-3d | Grade: %-3s |",
                rollNumber, name, age, grade);
    }
}

public class StudentRecordManager {

    private class Node {
        Student student;
        Node next;

        public Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentRecordManager() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("Added record: " + student.name + " at the beginning.");
    }

    public void addLast(Student student) {
        Node newNode = new Node(student);
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
        System.out.println("Added record: " + student.name + " at the end.");
    }

    public void addAtPosition(Student student, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position + ". List size is " + size + ".");
            return;
        }
        if (position == 1) {
            addFirst(student);
            return;
        }

        Node newNode = new Node(student);
        Node current = head;
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Added record: " + student.name + " at position " + position + ".");
    }

    public void deleteByRollNumber(int rollNumber) {
        if (isEmpty()) {
            System.out.println("Cannot delete. The list is empty.");
            return;
        }

        if (head.student.rollNumber == rollNumber) {
            String deletedName = head.student.name;
            head = head.next;
            size--;
            System.out.println("Deleted record for Roll No. " + rollNumber + " (" + deletedName + ").");
            return;
        }

        Node current = head;
        while (current.next != null && current.next.student.rollNumber != rollNumber) {
            current = current.next;
        }

        if (current.next != null) {
            String deletedName = current.next.student.name;
            current.next = current.next.next;
            size--;
            System.out.println("Deleted record for Roll No. " + rollNumber + " (" + deletedName + ").");
        } else {
            System.out.println("Student with Roll No. " + rollNumber + " not found.");
        }
    }

    public void displayRecords() {
        if (isEmpty()) {
            System.out.println("\n--- Student Records (List is Empty) ---");
            return;
        }

        System.out.println("\n--- Current Student Records (Size: " + size + ") ---");
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.student);
            current = current.next;
        }
        System.out.println("----------------------------------------------\n");
    }

    public static void main(String[] args) {
        StudentRecordManager manager = new StudentRecordManager();

        System.out.println("--- DEMONSTRATION OF ADD OPERATIONS ---");

        manager.addLast(new Student(101, "Alice Smith", 20, "A"));
        manager.addLast(new Student(103, "Charlie Brown", 22, "B+"));

        manager.addFirst(new Student(100, "Zoe Davis", 19, "A+"));

        manager.addAtPosition(new Student(102, "Bob Johnson", 21, "B"), 3);

        manager.displayRecords();

        System.out.println("\n--- DEMONSTRATION OF DELETE OPERATIONS ---");

        manager.deleteByRollNumber(100);

        manager.deleteByRollNumber(102);

        manager.deleteByRollNumber(999);

        manager.displayRecords();

        manager.deleteByRollNumber(101);
        manager.deleteByRollNumber(103);

        manager.displayRecords();
    }
}
