public class InsertionSortEmployeeIDs {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] employeeIDs = {1024, 1005, 1012, 1001, 1030, 1018, 1009, 1021};

        System.out.println("Original Employee IDs:");
        for (int id : employeeIDs) {
            System.out.print(id + " ");
        }

        insertionSort(employeeIDs);

        System.out.println("\nSorted Employee IDs (ascending):");
        for (int id : employeeIDs) {
            System.out.print(id + " ");
        }
    }
}