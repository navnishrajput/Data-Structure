import java.util.*;

class CircularBuffer {
    private int[] buffer;
    private int capacity;
    private int size;
    private int head;
    private int tail;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void add(int value) {
        if (size == capacity) {
            head = (head + 1) % capacity;
        } else {
            size++;
        }
        buffer[tail] = value;
        tail = (tail + 1) % capacity;
    }

    public List<Integer> getBuffer() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(buffer[(head + i) % capacity]);
        }
        return result;
    }
}

public class CircularBuffer {
    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(3);

        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        System.out.println("Buffer: " + buffer.getBuffer());

        buffer.add(4);
        System.out.println("After adding 4: " + buffer.getBuffer());

        buffer.add(5);
        System.out.println("After adding 5: " + buffer.getBuffer());
    }
}