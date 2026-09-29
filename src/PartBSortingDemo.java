public class PartBSortingDemo {

    private static final int[] REQUIRED_ARRAY = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

    public static void main(String[] args) {
        runSelectionSort();
        runInsertionSort();
        runMergeSort();
        runQuickSort();
    }

    private static int[] copyRequiredArray() {
        int[] copy = new int[REQUIRED_ARRAY.length];
        for (int i = 0; i < REQUIRED_ARRAY.length; i++) {
            copy[i] = REQUIRED_ARRAY[i];
        }
        return copy;
    }

    private static void runSelectionSort() {
        int[] a = copyRequiredArray();
        int comparisons = 0;
        int swaps = 0;

        System.out.println("\n=== B1: SELECTION SORT ===");
        System.out.print("Original: ");
        printArray(a);

        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                comparisons++;
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
                swaps++;
            }

            if (i < 3) {
                System.out.print("After pass " + (i + 1) + ": ");
                printArray(a);
            }
        }

        System.out.print("Sorted: ");
        printArray(a);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
    }

    private static void runInsertionSort() {
        int[] a = copyRequiredArray();
        int comparisons = 0;
        int shifts = 0;

        System.out.println("\n=== B2: INSERTION SORT ===");
        System.out.print("Original: ");
        printArray(a);

        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;

            if (i <= 3) {
                System.out.print("After pass " + i + ": ");
                printArray(a);
            }
        }

        System.out.print("Sorted: ");
        printArray(a);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Shifts: " + shifts);
    }

    private static void runMergeSort() {
        int[] a = copyRequiredArray();
        System.out.println("\n=== B3: MERGE SORT ===");
        System.out.println("Base case: a subarray of size 0 or 1 is already sorted.");
        System.out.print("Original: ");
        printArray(a);
        mergeSort(a, 0, a.length - 1);
        System.out.print("Sorted: ");
        printArray(a);
    }

    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = a[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }
        while (i < n1) {
            a[k++] = L[i++];
        }
        while (j < n2) {
            a[k++] = R[j++];
        }
    }

    private static void runQuickSort() {
        int[] a = copyRequiredArray();
        System.out.println("\n=== B4: QUICK SORT ===");
        System.out.println("Pivot rule: last element of the current subarray (Lomuto partition scheme).");
        System.out.print("Original: ");
        printArray(a);
        quickSort(a, 0, a.length - 1, new int[]{0});
        System.out.print("Sorted: ");
        printArray(a);
    }

    private static void quickSort(int[] a, int low, int high, int[] stage) {
        if (low < high) {
            int pivotIndex = partition(a, low, high);
            stage[0]++;
            if (stage[0] <= 2) {
                System.out.println(
                    "Partition stage " + stage[0]
                    + ": pivot=" + a[pivotIndex]
                    + ", pivotIndex=" + pivotIndex
                );
                System.out.print("Array after partition: ");
                printArray(a);
            }
            quickSort(a, low, pivotIndex - 1, stage);
            quickSort(a, pivotIndex + 1, high, stage);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }

    private static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
