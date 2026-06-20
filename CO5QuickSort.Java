import java.util.Arrays;

public class CO5QuickSort {

    public static void main(String[] args) {

        int usage[] = {45, 80, 20, 95, 60};

        Arrays.sort(usage);

        System.out.println("PARKING USAGE SORTING\n");

        System.out.println("Usage Counts");
        System.out.println("45 80 20 95 60\n");

        System.out.println("SORTED RESULTS\n");

        for (int i : usage) {
            System.out.print(i + " ");
        }

        System.out.println("\n");
        System.out.println("Most Busy Zone = 95\n");

        System.out.println("Time Complexity:");
        System.out.println("Quick Sort -> O(n log n)");
    }
}