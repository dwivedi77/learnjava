package abhi.learn.java.leetcode.bst;

/**
 * Created by Abhishek on 12/31/2021.
 */
public class BSTMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();
        BSTMain main = new BSTMain();

//        int[] input = new int[]{7, 8, 1, 2, 3, 4, 5, 6};
//        int[] input = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
//        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
//        int[] input = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
//        int[] input = new int[]{1, 2};

//        int[] input = new int[]{2,5,6,0,0,1,2};
//        int[] input = new int[]{2,5,6,6,6,0,0,1,2,2};
        int[] input = new int[]{7};
        Object output = main.search(input, 7);

        System.out.println("Answer=" + output);

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/search-in-rotated-sorted-array/
    private int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int pivot = findPivot(nums);
        int result=-1;
        int N  = nums.length;
        if (nums[pivot] <= target && nums[nums.length-1] >= target)
            result = binarySearch(nums, pivot, nums.length-1, target);
        else if(nums[0] <= target && nums[(pivot-1+N)%N] >= target )
            result = binarySearch(nums, 0, (pivot-1+N)%N, target);
        return result;
    }

    private int findPivot(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        int N = nums.length;
        while (start < end) {
            mid = start + (end - start) / 2;

            if ((nums[mid] < nums[(mid - 1 + N) % N]) && (nums[mid] <= nums[(mid + 1 + N) % N]))
                return mid;
            if (nums[end] > nums[start])
                return start;
            if (nums[mid] > nums[start])
                start = mid + 1;
            else
                end = mid;
        }
        return mid + 1;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start == end && nums[start] == target){
            return start;
        }
        int mid = 0;

        while (start < end){
            mid = start + (end-start)/2;
            if (nums[mid] == target) return mid;
            else if (target < nums[mid]){
                return binarySearch(nums, start, mid, target);
            }else if(nums[mid] < target) {
                return binarySearch(nums, mid+1, end, target);
            }
        }
        return -1;
    }


    /// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    public boolean searchRotatedWithRepeated(int[] nums, int target) {
        int result = -1;
        int start=0; int end=nums.length-1;

        while (start<=end){
            int mid = start + (end-start)/2;
            if (nums[mid] == target) return true;

            if ( nums[start] < target && nums[mid] > target)
                end = mid;
            else if(nums[mid] < target && nums[end] > target)
                start = mid+1;
            else
                end--;

        }

        return false;
    }

    private int findPivotWithRepeated(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        int N = nums.length;
        while (start < end) {
            mid = start + (end - mid) / 2;

            if ((nums[mid] < nums[(mid - 1 + N) % N]) && (nums[mid] <= nums[(mid + 1 + N) % N]))
                return mid;
            if (nums[end] > nums[start])
                return start;
            if (nums[mid] > nums[start])
                start = mid + 1;
            else
                end = mid;
        }
        return mid + 1;
    }



}