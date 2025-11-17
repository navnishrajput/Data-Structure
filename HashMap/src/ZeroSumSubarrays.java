import java.util.HashMap;

public class ZeroSumSubarrays {

    public static int countZeroSumSubarrays(int[] arr) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int count = 0;
        int sum = 0;

        sumMap.put(0, 1);

        for (int num : arr) {
            sum += num;
            if (sumMap.containsKey(sum)) {
                count += sumMap.get(sum);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 5, 5, 0, 0};
        System.out.println("Number of zero sum subarrays: " + countZeroSumSubarrays(arr));
    }
}