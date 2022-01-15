package abhi.learn.java.coursera.specialization.algo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Abhishek on 7/6/2020.
 */
public class AlgoMain {

    public static void main(String[] args) throws Exception {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        AlgoMain main = new AlgoMain();
        int[] input = main.readArrayFromFile();
//        int[] input = new int[]{3,2,5};
        int[] cnt = new int[]{0};
        main.quickSort(input, 0, input.length-1, cnt);

//        Object output = main.quickSort(new int[]{7,3,6,1,2,4,8,11,9}, 0, 8);
//        System.out.println("Answer="+output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }


//    private int Krager
    private int[] readArrayFromFile() throws Exception{
        InputStream is = new FileInputStream("D:\\Abhishek\\Learning\\Algorithm\\coursera_algorithm\\week3\\QuickSort.txt");
        Scanner scan = new Scanner(is);
        int[] input = new int[10000]; int idx = 0;
        while (scan.hasNext()){
            input[idx++] = Integer.parseInt(scan.next());
        }
        return input;
    }

    private void quickSort(int[] nums, int start, int end, int[] cnt){
        if (nums == null || nums.length<=1) return;
        if (end-start<1) return;
        cnt[0]+=(end-start);
        int pivot = start;
//        int pivot = findMedianIndex(nums, start, end);
//        int pivot = end;

        for (int i = start+1; i <= end;) {
            if ( (i > pivot && nums[i] < nums[pivot]) || (i < pivot && nums[i] > nums[pivot])){
                int temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot] = temp;
                temp = i;
                i = pivot+1;
                pivot = temp;
            }else i++;
        }
        quickSort(nums, start, pivot-1, cnt);
        quickSort(nums, pivot+1, end, cnt);
    }

    private int findMedianIndex(int[] nums, int s, int e){
        int med = findMedian(nums[s], nums[(e-s)/2], nums[e]);
        if (med == nums[s]) return s;
        else if (med == nums[e]) return e;
        else return (e-s)/2;
    }

    private int findMedian(int x, int y, int z){
        int[] ary = new int[]{x,y,x};
        Arrays.sort(ary);
        return ary[1];
    }

    private int[] mergeSort(int[] nums){
        if (nums.length == 1) return nums;
        if (nums.length == 2){
            if (nums[0] > nums[1]){
                int temp = nums[0];
                nums[0] = nums[1];
                nums[1] = temp;
            }
            return nums;
        }
        int mid = (nums.length-1)/2;
        int[] first = mergeSort(Arrays.copyOfRange(nums, 0, mid));
        int[] second = mergeSort(Arrays.copyOfRange(nums, mid, nums.length));

        int[] output = new int[nums.length];
        int i=0,j=0, idx=0;
        while (i<first.length && j<second.length){
            if (first[i] < second[j]){
                output[idx++] = first[i++];
            }else if(first[i] > second[j]){
                output[idx++] = second[j++];
            }else {
                output[idx++] = first[i++];
                output[idx++] = second[j++];
            }
        }
        while (i<first.length){
            output[idx++] = first[i++];
        }
        while (j<second.length){
            output[idx++] = second[j++];
        }
        return output;
    }

    private int[] merge(int[] first, int[] second){
        int[] output = new int[first.length+second.length];
        int i=0,j=0, idx=0;
        while (i<first.length && j<second.length){
            if (first[i] < second[j]){
                output[idx++] = first[i++];
            }else if(first[i] > second[j]){
                output[idx++] = second[j++];
            }else {
                output[idx++] = first[i++];
                output[idx++] = second[j++];
            }
        }
        while (i<first.length){
            output[idx++] = first[i++];
        }
        while (j<second.length){
            output[idx++] = second[j++];
        }
        return output;
    }

    private long countInversions(int[] nums){
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) { /// nums[i] > nums[j]
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }
}
