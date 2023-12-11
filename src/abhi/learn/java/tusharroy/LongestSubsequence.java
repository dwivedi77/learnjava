package abhi.learn.java.tusharroy;

/**
 * Created by Abhishek on 3/12/2019.
 */
public class LongestSubsequence {
    public static void main(String[] args) {
//        int output = findLongestSubsequenceLength(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
        int output = findLongestSubsequenceLength(new int[]{4, 50, 3, 10, 7, 40, 80, 91});
        System.out.println("output = " + output);
    }

    private static int findLongestSubsequenceLength(int[] input){
        int[] temp = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            temp[i] = 1;
        }

        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i]>=input[j]){
                    if (temp[j]>= temp[i])
                        temp[i]=temp[j]+1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > max){
                max = temp[i];
            }
        }
        return max;
    }

    //https://www.youtube.com/watch?v=1RpMc3fv0y4
    private static int[] findLongestIncreasingSubsequence(int[] input){
        int[] subseq = new int[input.length];
        int[] parent = new int[input.length];
        int len = 0;

        for (int i = 0; i < input.length; i++) {
            if (i==0){
                subseq[i]=input[i];
                parent[i]=i; //keeping the parent's idx
                len++;
                continue;
            }
            for (int j = 0; j < i; j++) {
                
            }

        }
        return null;
    }

}
