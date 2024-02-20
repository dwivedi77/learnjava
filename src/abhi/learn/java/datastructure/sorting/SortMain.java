package abhi.learn.java.datastructure.sorting;

import java.util.Arrays;

/**
 * Created by Abhishek on 7/7/2016.
 */
public class SortMain {

    public static void main(String[] args) {
//        int[] unsorted = {2,1,1,1};
//        int[] unsorted = {25, 10, 8, 67, 11, 3, 99, -1, 4, 36, 100, 47, 85, -10, -10, 123, 45};
        int[] unsorted = {0, 0, 0, -1,-2,5, 3, 1000};
//        quickSort(unsorted, 0, unsorted.length-1);
//        mergesort(unsorted, 0, unsorted.length);
        int[] sorted = new int[unsorted.length];
        mergesort(unsorted, 0, unsorted.length-1, sorted);
//        merge(unsorted, 0, 3, 4, 7, sorted);
        System.out.println("sorted = " + Arrays.toString(sorted));
    }

    //this implementation works. 01_15_2024
    private static void quickSort(int[] unsorted, int begin, int end){
        if (begin>=end) return;
        int pivot = end;
        int i = begin-1;
        for (int j = begin; j < end; j++) {
            if (unsorted[j] < unsorted[pivot]){
                i++;
                swap(unsorted, i, j);
            }
        }
        i++;
        swap(unsorted, i, pivot);
        quickSort(unsorted, begin, i-1);
        quickSort(unsorted, i+1, end);
    }

    private static void insertionSort(int[] unsorted){
        for (int i = 1; i < unsorted.length; i++) {
            int x = i;
            for (int j = x-1; j >= 0 ; j--) {
                if (unsorted[j] > unsorted[x]){
                    //swap
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[x];
                    unsorted[x] = temp;
                    x = j;
                }
            }
        }
    }

    private static void selectionSort(int[] unsorted){
        for (int i = 0; i < unsorted.length-1; i++) {
            for (int j = i+1; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j]){
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
    }
    private static int[] temp(int[] unsorted) {
        int[] sorted = new int[unsorted.length];
        mergesort(unsorted, 0, unsorted.length, sorted);
        return sorted;
    }


    private static int[] mergesort(int[] unsorted, int begin, int end, int[] sorted) {
        if (end-begin<=1){
            if (unsorted[end] < unsorted[begin]){
                swap(unsorted, begin, end);
            }
            return sorted;
        }
        if (end-begin > 1){
            int x1 = begin; int x2 = (begin+end)/2;
            int y1 = 1+ (begin+end)/2; int y2 = end;
            mergesort(unsorted, begin, (begin+end)/2, sorted);
            mergesort(unsorted, 1+ (begin+end)/2, end, sorted);
            merge(unsorted, x1, x2, y1, y2, sorted);
        }
    return sorted;
    }

    private static void merge(int[] unsorted, int x1, int x2, int y1, int y2, int[] sorted) {
        int i = x1, j=x1;
        while (x1<=x2 && y1<=y2){
            if (unsorted[x1] > unsorted[y1]){
                sorted[i++] = unsorted[y1];
                y1++;
            } else if (unsorted[x1] < unsorted[y1]) {
                sorted[i++] = unsorted[x1];
                x1++;
            }else {//both are equal
                sorted[i++] = unsorted[x1];
                sorted[i++] = unsorted[y1];
                x1++;y1++;
            }
        }
        while (x1<=x2){
            sorted[i++] = unsorted[x1++];
        }
        while (y1<=y2){
            sorted[i++] = unsorted[y1++];
        }
        //copy to original
        for (; j <= y2; j++) {
            unsorted[j] = sorted[j];
        }
    }


    private static void swap ( int[] unsorted, int i, int j){
        if (i==j) return;
            int x = unsorted[i];
            unsorted[i] = unsorted[j];
            unsorted[j] = x;
        }

}

