import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] list = new int[5000000];
        for (int i = 0; i < list.length; i++){
            list[i] = (int)(Math.random() * 1000000);
            //System.out.print(list[i] + " ");
        }
        System.out.println();
        getTime(list);
        /*System.out.println();
        System.out.println("Insertion Sort:");
        insertion(list);
        print(list);
        System.out.println("Bubble Sort:");
        bubble(list);
        print(list);
        System.out.println("Merge Sort:");
        merge(list, list.length);
        print(list);
        System.out.println("Quick Sort:");
        quick(list, 0, list.length - 1);
        print(list);
        System.out.println("Radix Sort:");
        radix(list, list.length);
        print(list);

         */
    }

    public static void insertion(int[] list){
        for (int i = 1; i < list.length; ++i) {
            int key = list[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j];
                j = j - 1;
            }
            list[j + 1] = key;
        }
    }

    public static void bubble(int[] list){
        int temp;

            for (int i = 0; i < list.length - 1; i++) {
                for(int j = 0; j < list.length - i - 1; j++) {
                    if (list[j] > list[j + 1]) {
                        temp = list[j];
                        list[j] = list[j + 1];
                        list[j + 1] = temp;
                    }
                }
            }
    }

    public static void merge(int[] list, int length){
        if (length < 2) {
            return;
        }
        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];

        for (int i = 0; i < mid; i++){
            left[i] = list[i];
        }

        for (int i = mid; i < length; i++) {
            right[i - mid] = list[i];
        }
        merge(left, mid);
        merge(right, length - mid);

        sort(list, left, right, mid, length - mid);
    }

    public static void sort(int[]list, int[] leftList, int[] rightList, int left, int right){
        int i = 0, j = 0, k = 0;
        while (i < left && j < right){
            if (leftList[i] <= rightList[j]){
                list[k++] = leftList[i++];
            }
            else {
                list[k++] = rightList[j++];
            }
        }
        while (i < left) {
            list[k++] = leftList[i++];
        }
        while (j < right) {
            list[k++] = rightList[j++];
        }
    }

    public static void swap(int[] list, int i, int j){
        int temp = list[i];
        list[i] = list [j];
        list[j] = temp;
    }

    public static int partition(int[] list, int low, int high){
        int pivot = list[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++){
            if (list[j] < pivot){
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return (i + 1);
    }

    public static void quick(int[] list, int low, int high){
        if (low < high){
            int partition = partition(list, low, high);
            quick(list, low, partition - 1);
            quick(list, partition + 1, high);
        }
    }

    public static int getMax(int list[], int n){
        int max = list[0];
        for (int i = 1; i < n; i++){
            if (list[i] > max)
                max = list[i];
        }
        return max;
    }

    public static void countSort(int[] list, int n , int exp){
        int output[] = new int[n];
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++)
            count[(list[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--){
            output[count[(list[i] / exp) % 10] - 1] = list[i];
            count[(list[i] / exp) % 10]--;
        }
        for (int i = 0; i < n; i++)
            list[i] = output[i];
    }

    public static void radix(int list[], int n){
        int m = getMax(list, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(list, n, exp);
    }

    public static void print(int[] list){
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    public static void getTime(int[] list){
        long startTime = System.currentTimeMillis();
        bubble(list);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
    }
}
