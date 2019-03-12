package abhi.learn.java.blackrock;

import abhi.learn.java.core.lang.Outer;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by Abhishek on 3/11/2019.
 */
public class Solution2 {
    /*
     * Complete the function below.
     */
    static String decode(String encodedMessage) {
        StringBuilder sb = new StringBuilder(encodedMessage);
        sb.reverse();
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < encodedMessage.length(); i++) {
            char c = sb.charAt(i);
            decoded.append((char)((c-i)/2));
        }
        return decoded.toString();
    }

    private static String encode(String text) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            b.append(c += c + i);
        }
        return b.reverse().toString();
    }
    public static void main(String[] args) throws IOException {
//        String encoded = encode("Abhishek Dwivedi");
//        System.out.println("encoded = " + encoded);
//        String decoded = decode(encoded);
//        System.out.println("decoded = " + decoded);
        int[] input = new int[]{1200, 300, 500, 600, 700};
        int[][] output = aggregatePools(input);
        System.out.println("output = " + output);
    }

    private static int[][] aggregatePools(int[] poolList) {
        Arrays.sort(poolList);
        HashMap<Integer, Object> map = new HashMap<>();
        int sum = 0; int cnt = 0; int idx = 0;
        for (int i = poolList.length-1; i >=0 ; i--) {
            if (poolList[i] >= 1000){
                map.put(i, new int[poolList[i]]);
                idx = i-1; //next start point;
            }else{
                sum = sum + poolList[i]; cnt++;
                if (cnt > 3){
                    sum = 0; cnt = 0; continue;
                }
                if (sum >= 1000){
                    int[] ary = new int[idx - i + 1];
                    for (int j = i; j <= idx; j++) {
                        ary[j] = poolList[j];
                    }
                    map.put(i, ary);
                    sum = 0; cnt = 0; continue;
                }
            }
        }

        int[][] output = new int[map.size()][];
        idx = 0;
        for (int num: map.keySet()) {
            output[idx++] = (int[]) map.get(num);
        }
        return output;
    }

//    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = null;
//        if (fileName != null) {
//            bw = new BufferedWriter(new FileWriter(fileName));
//        } else {
//            bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        }
//
//        String res;
//        String encodedMessage;
//        try {
//            encodedMessage = in.nextLine();
//        } catch (Exception e) {
//            encodedMessage = null;
//        }
//
//        res = decode(encodedMessage);
//        bw.write(res);
//        bw.newLine();
//
//        bw.close();
//    }


}
