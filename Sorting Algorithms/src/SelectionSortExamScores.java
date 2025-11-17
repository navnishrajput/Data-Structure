public class SelectionSortExamScores {
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] examScores = {78, 92, 65, 85, 88, 72, 95, 60, 82, 75};

        System.out.println("Original exam scores:");
        for (int score : examScores) {
            System.out.print(score + " ");
        }

        selectionSort(examScores);

        System.out.println("\nSorted exam scores (ascending):");
        for (int score : examScores) {
            System.out.print(score + " ");
        }
    }
}