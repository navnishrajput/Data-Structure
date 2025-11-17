public class CountingSortStudentAges {
    public static void countingSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] studentAges = {15, 12, 16, 14, 17, 13, 15, 16, 14, 18, 12, 15, 17, 16, 13};

        System.out.println("Original student ages:");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }

        countingSort(studentAges);

        System.out.println("\nSorted student ages (ascending):");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }
    }
}