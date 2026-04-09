import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSortingSystem {

    // -------- MERGE SORT (ASC, STABLE) --------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // -------- QUICK SORT (DESC + VOLATILITY ASC) --------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {

        // MEDIAN-OF-3 pivot selection
        int mid = (low + high) / 2;
        Asset pivot = medianOfThree(arr[low], arr[mid], arr[high]);

        int i = low - 1;

        for (int j = low; j <= high; j++) {
            if (compare(arr[j], pivot)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        return i;
    }

    // Comparator logic: DESC returnRate, ASC volatility
    private static boolean compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return a.returnRate > b.returnRate;
        }
        return a.volatility < b.volatility;
    }

    private static Asset medianOfThree(Asset a, Asset b, Asset c) {
        if ((a.returnRate > b.returnRate && a.returnRate < c.returnRate) ||
            (a.returnRate < b.returnRate && a.returnRate > c.returnRate)) {
            return a;
        }
        if ((b.returnRate > a.returnRate && b.returnRate < c.returnRate) ||
            (b.returnRate < a.returnRate && b.returnRate > c.returnRate)) {
            return b;
        }
        return c;
    }

    public static void main(String[] args) {

        Asset[] assets = {
            new Asset("AAPL", 12, 5),
            new Asset("TSLA", 8, 9),
            new Asset("GOOG", 15, 4)
        };

        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        Asset[] quickArr = Arrays.copyOf(assets, assets.length);

        // Merge Sort
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (ASC): " + Arrays.toString(mergeArr));

        // Quick Sort
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (DESC): " + Arrays.toString(quickArr));
    }
}