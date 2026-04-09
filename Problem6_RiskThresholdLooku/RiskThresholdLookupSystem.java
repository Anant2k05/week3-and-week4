
public class RiskThresholdLookupSystem {

    // -------- LINEAR SEARCH --------
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Comparisons: " + comparisons);
        return -1;
    }

    // -------- LOWER BOUND (first >= target) --------
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // -------- UPPER BOUND (first > target) --------
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // -------- FLOOR (largest <= target) --------
    public static Integer floor(int[] arr, int target) {
        int idx = upperBound(arr, target) - 1;
        if (idx >= 0) return arr[idx];
        return null;
    }

    // -------- CEILING (smallest >= target) --------
    public static Integer ceiling(int[] arr, int target) {
        int idx = lowerBound(arr, target);
        if (idx < arr.length) return arr[idx];
        return null;
    }

    // -------- INSERTION POINT --------
    public static int insertionPoint(int[] arr, int target) {
        return lowerBound(arr, target);
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        int target = 30;

        // Linear search (unsorted use-case)
        int linearResult = linearSearch(risks, target);
        System.out.println("Linear Result: " + linearResult);

        // Binary search variants
        int insertPos = insertionPoint(risks, target);
        Integer floorVal = floor(risks, target);
        Integer ceilVal = ceiling(risks, target);

        System.out.println("Insertion Point: " + insertPos);
        System.out.println("Floor: " + floorVal);
        System.out.println("Ceiling: " + ceilVal);
    }
}