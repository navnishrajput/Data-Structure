import java.util.*;

public class RotateList {
    public static <T> void rotateList(List<T> list, int positions) {
        if (list.isEmpty()) return;

        int n = list.size();
        positions = positions % n;
        if (positions < 0) positions += n;

        Collections.rotate(list, -positions);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original list: " + list);

        rotateList(list, 2);
        System.out.println("After rotating by 2: " + list);

        rotateList(list, -1);
        System.out.println("After rotating by -1: " + list);
    }
}