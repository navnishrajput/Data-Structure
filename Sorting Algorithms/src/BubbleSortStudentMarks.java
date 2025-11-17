public class BubbleSortStudentMarks {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] studentMarks = {85, 92, 78, 65, 95, 88, 72, 60};

        System.out.println("Original marks:");
        for (int mark : studentMarks) {
            System.out.print(mark + " ");
        }

        bubbleSort(studentMarks);

        System.out.println("\nSorted marks (ascending):");
        for (int mark : studentMarks) {
            System.out.print(mark + " ");
        }
    }
}