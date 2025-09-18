public class LibraryBookSorting {

    // ----------- Team Conquer (merge method) ------------
    public static void merge(long[] arr, int left, int mid, int right) {
        // Sizes of subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        long[] L = new long[n1];
        long[] R = new long[n2];

        // Copy data into temporary arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge subarrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements of L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy any remaining elements of R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // ----------- Team Divide + Orchestrate (mergeSort) ------------
    public static void mergeSort(long[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // ----------- Team Main Program ------------
    public static void main(String[] args) {
        // Array of ISBN numbers
        long[] ISBNs = {
            9780131103627L,
            9780321573513L,
            9780262033848L,
            9780134092669L,
            9780201616224L
        };

        // Print unsorted ISBNs
        System.out.println("Unsorted ISBNs:");
        for (long isbn : ISBNs) {
            System.out.println(isbn);
        }

        // Sort using merge sort
        mergeSort(ISBNs, 0, ISBNs.length - 1);

        // Print sorted ISBNs
        System.out.println("\nSorted ISBNs:");
        for (long isbn : ISBNs) {
            System.out.println(isbn);
        }
    }
}
