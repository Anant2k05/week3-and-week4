import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientRiskRankingSystem {

    // Bubble Sort (Ascending by riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort swaps: " + swaps);
    }

    // Insertion Sort (Descending by riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                  (arr[j].riskScore < key.riskScore ||
                  (arr[j].riskScore == key.riskScore &&
                   arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Get Top N High Risk Clients
    public static void topRiskClients(Client[] arr, int k) {
        System.out.println("Top " + k + " high-risk clients:");

        for (int i = 0; i < Math.min(k, arr.length); i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {

        Client[] original = {
            new Client("clientC", 80, 5000),
            new Client("clientA", 20, 2000),
            new Client("clientB", 50, 3000)
        };

        // Copy arrays (don’t reuse — same mistake again otherwise)
        Client[] bubbleArr = Arrays.copyOf(original, original.length);
        Client[] insertionArr = Arrays.copyOf(original, original.length);

        // Bubble Sort
        bubbleSort(bubbleArr);
        System.out.println("Bubble Sorted: " + Arrays.toString(bubbleArr));

        // Insertion Sort
        insertionSort(insertionArr);
        System.out.println("Insertion Sorted: " + Arrays.toString(insertionArr));

        // Top risk clients
        topRiskClients(insertionArr, 3);
    }
}