import java.util.*;

class MyStack<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(T x) {
        queue2.add(x);
        while (!queue1.isEmpty()) {
            queue2.add(queue1.poll());
        }
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public T pop() {
        return queue1.poll();
    }

    public T top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

public class StackUsingQueues {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Is empty: " + stack.empty());
    }
}