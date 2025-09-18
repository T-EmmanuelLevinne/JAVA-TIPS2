public class LibraryBookSorting {

    // ==============================
    // Team Conquer (Students 1–8)
    // ==============================
    public static void merge(long[] arr, int left, int mid, int right) {
        // Sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        long[] L = new long[n1];
        long[] R = new long[n2];

        // Copy data into temp arrays
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        // Merge temp arrays back into arr[left..right]
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

        // Copy remaining elements of L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // ======================================
    // Team Divide + Orchestrate (Students 9–13)
    // ======================================
    public static void mergeSort(long[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Recursively sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // ==============================
    // Team Main Program (Students 14–17)
    // ==============================
    public static void main(String[] args) {
        // ISBN array (unsorted)
        long[] isbns = {
            9780131103627L,
            9780321573513L,
            9780262033848L,
            9780134092669L,
            9780201616224L
        };

        // Print unsorted array
        System.out.println("Unsorted ISBNs:");
        for (long isbn : isbns) {
            System.out.println(isbn);
        }

        // Sort using merge sort
        mergeSort(isbns, 0, isbns.length - 1);

        // Print sorted array
        System.out.println("\nSorted ISBNs:");
        for (long isbn : isbns) {
            System.out.println(isbn);
        }
    }
}
