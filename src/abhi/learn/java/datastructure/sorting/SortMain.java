package abhi.learn.java.datastructure.sorting;

import java.util.Arrays;

/**
 * Created by Abhishek on 7/7/2016.
 */
public class SortMain {

    public static void main(String[] args) {
        int[] unsorted = {25, 10, 5, 3, 8, 18, 9, 11};
        mergesort(unsorted);
        quickSort2(unsorted, 0, unsorted.length - 1);
        System.out.println("sorted = " + Arrays.toString(unsorted));
    }

    private static void mergesort(int[] unsorted) {
        int[] sorted = new int[unsorted.length];

        int split = unsorted.length/2;
        splitSortMerge(unsorted, 0, split-1, split, unsorted.length-1, sorted);
    }

    private static void splitSortMerge(int[] unsorted, int start1, int end1, int start2, int end2, int[] sorted) {
        if (end1-start1 > 2){
            int temp = start1+ (end1-start1)/2;
            splitSortMerge(unsorted, start1, temp, temp+1, end1, sorted );
        }
        if (unsorted[start1] > unsorted[end1]){
            sorted[start1] = unsorted[end1];
            sorted[end1] = unsorted[start1];
        }
        if (end2-start2 > 2){
            int temp = start2+ (end2-start2)/2;
            splitSortMerge(unsorted, start2, temp, temp+1, end2, sorted );
        }
        if (unsorted[start2] > unsorted[end2]){
            sorted[start2] = unsorted[end2];
            sorted[end2] = unsorted[start2];
        }

        ///merge
        boolean firstDone = false;
        boolean secondDone = false;
        while(!firstDone && !secondDone){
            if (unsorted[start1] > unsorted[start2]){
                sorted[start1] = unsorted[start2];
                start2++;
            }else {
                sorted[start1] = unsorted[start1];
                start1++;
            }
            if (start1 > end1){
                firstDone = true;
            }
            if (start2 > end2){
                secondDone = true;
            }
        }
        if (firstDone){
            for (int i = start2; i < end2; i++) {
//                sorted[]
            }
        }
    }

    private static void quickSort2(int[] un, int i, int j) {
        if (un == null || (i - j) == 0) return;
        if (j - i == 1) {
            if (un[i] > un[j]) swap(un, i, j);
        }
        int pivot = (i + j) / 2;
        int x = i, y = j;
        while (x <= pivot && y > pivot) {
            if (un[x] <= un[pivot]) {
                x++;
                continue;
            }
            if (un[y] > un[pivot]) {
                y--;
                continue;
            }
            swap(un, x, y);
            x++;
            y--;
        }
        quickSort2(un, i, pivot);
        quickSort2(un, pivot + 1, j);
    }

        private static void quickSort ( int[] unsorted, int low, int high){
            int pivot = unsorted[low + (high - low) / 2];
            int i = low, j = high;
            while (i <= j) {

                while (unsorted[i] < pivot) i++;
                while (unsorted[j] > pivot) j--;
                if (i <= j) {
                    swap(unsorted, i, j);
                    i++;
                    j--;
                }
            }
            if (low < j)
                quickSort(unsorted, low, j);
            if (i < high)
                quickSort(unsorted, i, high);
//        return unsorted;
        }

        private static void swap ( int[] unsorted, int i, int j){
            int x = unsorted[i];
            unsorted[i] = unsorted[j];
            unsorted[j] = x;
        }



}

