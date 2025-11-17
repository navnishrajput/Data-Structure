public class QuickSortProductPrices {
    public static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        double[] productPrices = {299.99, 149.50, 499.00, 79.99, 199.25, 349.75, 89.50, 599.00};

        System.out.println("Original product prices:");
        for (double price : productPrices) {
            System.out.print(price + " ");
        }

        quickSort(productPrices, 0, productPrices.length - 1);

        System.out.println("\nSorted product prices (ascending):");
        for (double price : productPrices) {
            System.out.print(price + " ");
        }
    }
}