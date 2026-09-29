import java.util.Random;

public class SortingExperiment {
    static long comparisons;
    static long swaps;
    static long shifts;

    static void selectionSort(int[] a) {
        comparisons = 0;
        swaps = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                comparisons++;
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
                swaps++;
            }
        }
    }

    static void insertionSort(int[] a) {
        comparisons = 0;
        shifts = 0;
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
        }
    }

    static void mergeSort(int[] a) {
        comparisons = 0;
        mergeSort(a, 0, a.length - 1);
    }

    static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    static void merge(int[] a, int left, int mid, int right) {
        int[] L = new int[mid - left + 1];
        int[] R = new int[right - mid];

        for (int i = 0; i < L.length; i++) {
            L[i] = a[left + i];
        }
        for (int j = 0; j < R.length; j++) {
            R[j] = a[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < L.length && j < R.length) {
            comparisons++;
            if (L[i] <= R[j]) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }

        while (i < L.length) {
            a[k++] = L[i++];
        }
        while (j < R.length) {
            a[k++] = R[j++];
        }
    }

    static void quickSort(int[] a) {
        comparisons = 0;
        swaps = 0;
        quickSort(a, 0, a.length - 1);
    }

    static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(a, low, high);
            quickSort(a, low, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, high);
        }
    }

    static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                swaps++;
            }
        }

        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        swaps++;
        return i + 1;
    }

    static int[] copy(int[] original) {
        int[] result = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i];
        }
        return result;
    }

    static void run(String name, int[] original, int mode) {
        int[] data = copy(original);
        long start = System.nanoTime();

        switch (mode) {
            case 1:
                selectionSort(data);
                break;
            case 2:
                insertionSort(data);
                break;
            case 3:
                mergeSort(data);
                break;
            case 4:
                quickSort(data);
                break;
            default:
                throw new IllegalArgumentException("Unknown sorting mode: " + mode);
        }

        long end = System.nanoTime();
        System.out.printf(
            "%-15s | Comparisons: %-8d | Time: %d ns%n",
            name, comparisons, end - start
        );
    }

    public static void runExperiment() {
        int[] sizes = {20, 50, 100, 500};
        Random random = new Random(42);
        int[] original100 = null;

        System.out.println("\n========== SORTING EXPERIMENT ==========");

        for (int size : sizes) {
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = random.nextInt(1000) + 1;
            }

            if (size == 100) {
                original100 = copy(original);
            }

            System.out.println("\nINPUT SIZE: " + size);
            run("Selection Sort", original, 1);
            run("Insertion Sort", original, 2);
            run("Merge Sort", original, 3);
            run("Quick Sort", original, 4);
        }

        // Additional test required by the brief:
        // sort the same original 100-element array, then swap exactly
        // five non-overlapping pairs of neighbouring values.
        int[] almostSorted = copy(original100);
        mergeSort(almostSorted); // preparation only; not timed

        int swapsMade = 0;
        for (int i = 0; i < almostSorted.length - 1 && swapsMade < 5; i += 2) {
            if (almostSorted[i] != almostSorted[i + 1]) {
                int temp = almostSorted[i];
                almostSorted[i] = almostSorted[i + 1];
                almostSorted[i + 1] = temp;
                swapsMade++;
            }
        }

        System.out.println("\n===== ALMOST-SORTED ARRAY (100) =====");
        run("Selection Sort", almostSorted, 1);
        run("Insertion Sort", almostSorted, 2);
        run("Merge Sort", almostSorted, 3);
        run("Quick Sort", almostSorted, 4);
    }
}
