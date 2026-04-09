import java.util.*;

public class AccountIDLookupSystem {

    // -------- LINEAR SEARCH (FIRST OCCURRENCE) --------
    public static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First Comparisons: " + comparisons);
        return -1;
    }

    // -------- LINEAR SEARCH (LAST OCCURRENCE) --------
    public static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        int result = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                result = i;
            }
        }

        System.out.println("Linear Last Comparisons: " + comparisons);
        return result;
    }

    // -------- BINARY SEARCH (ANY OCCURRENCE) --------
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Comparisons: " + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Comparisons: " + comparisons);
        return -1;
    }

    // -------- COUNT OCCURRENCES USING BINARY SEARCH --------
    public static int countOccurrences(String[] arr, String target) {
        int first = findFirst(arr, target);
        int last = findLast(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    private static int findFirst(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) == 0) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    private static int findLast(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) == 0) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear search
        int first = linearFirst(logs, "accB");
        int last = linearLast(logs, "accB");

        System.out.println("Linear First Index: " + first);
        System.out.println("Linear Last Index: " + last);

        // Sort before binary search
        Arrays.sort(logs);

        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary Index: " + index);
        System.out.println("Count: " + count);
    }
}