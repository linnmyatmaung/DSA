/*
 * @Author : Linn Myat Maung
 * @Date   : 5/21/2025
 * @Time   : 10:03 AM
 */

package SortingAlgorithms;
/* Choosing Pivot forQuick SOrt
   *First element
   *Last element
   *Random element
   *Median-of-three
   *Median of the entire list
*/

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {33, 10, 55, 71, 29, 3, 18};
        System.out.println("Original array:");
        printArray(array);

        quickSort(array, 0, array.length - 1);

        System.out.println("Sorted array:");
        printArray(array);
    }

    // Main quicksort function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Choose pivot using median-of-three and partition the array
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high); // Move pivot to end
            int partitionIndex = partition(arr, low, high);

            // Recursively sort left and right parts
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    // Partition the array around the pivot (which is at 'high')
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high); // Move pivot to correct position
        return i + 1;
    }

    // Median-of-three pivot selection
    public static int medianOfThree(int[] arr, int low, int high) {
        int mid = (low + high) / 2;

        int a = arr[low];
        int b = arr[mid];
        int c = arr[high];

        if ((a - b) * (c - a) >= 0) return low;
        else if ((b - a) * (c - b) >= 0) return mid;
        else return high;
    }

    // Swap helper
    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Print array
    public static void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}
