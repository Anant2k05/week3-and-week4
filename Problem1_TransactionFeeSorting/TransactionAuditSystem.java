import java.time.LocalTime;
import java.util.*;

class Transaction {
    String id;
    double fee;
    LocalTime timestamp;

    Transaction(String id, double fee, String ts) {
        this.id = id;
        this.fee = fee;
        this.timestamp = LocalTime.parse(ts);
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionAuditSystem {

    // Bubble Sort (stable, by fee)
    public static void bubbleSortByFee(ArrayList<Transaction> list) {
        int n = list.size();
        int swaps = 0, passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort → passes: " + passes + ", swaps: " + swaps);
    }

    // Insertion Sort (stable, fee + timestamp)
    public static void insertionSort(ArrayList<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                  (list.get(j).fee > key.fee ||
                  (list.get(j).fee == key.fee &&
                   list.get(j).timestamp.isAfter(key.timestamp)))) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    // Outlier Detection
    public static void findOutliers(ArrayList<Transaction> list) {
        System.out.println("High-fee outliers:");

        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) System.out.println("None");
    }

    public static void main(String[] args) {

        ArrayList<Transaction> original = new ArrayList<>();

        original.add(new Transaction("id1", 10.5, "10:00"));
        original.add(new Transaction("id2", 25.0, "09:30"));
        original.add(new Transaction("id3", 5.0, "10:15"));

        // IMPORTANT: don't reuse same list (most people mess this up)
        ArrayList<Transaction> bubbleList = new ArrayList<>(original);
        ArrayList<Transaction> insertionList = new ArrayList<>(original);

        // Bubble Sort
        bubbleSortByFee(bubbleList);
        System.out.println("Bubble Sorted: " + bubbleList);

        // Insertion Sort
        insertionSort(insertionList);
        System.out.println("Insertion Sorted: " + insertionList);

        // Outliers
        findOutliers(original);
    }
}