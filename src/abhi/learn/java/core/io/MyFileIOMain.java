package abhi.learn.java.core.io;

import java.io.*;
import java.util.Arrays;

public class MyFileIOMain {
    public static void main(String[] args) throws Exception{
        MyFileIOMain main = new MyFileIOMain();
        main.testMyBufferedWriter();
    }

    private void testMyBufferedWriter() throws IOException {
        File f1 = new File("F:\\Abhishek\\Learning\\Technology\\Java.txt");
        FileInputStream is = new FileInputStream(f1);

        MyFile f2 = new MyFile("F:\\Abhishek\\Learning\\Technology\\Copy_of_Java.txt");
        MyBufferredFile bFile = new MyBufferredFile(f2, 20000);

        int value = is.read();
        byte[] valAry = new byte[1000];
        int idx = 0;
        while (value != -1){
                bFile.write(value);
                idx = 0;
            }
            value = is.read();
        bFile.flush();
    }
    private void testBytesAndChars(){
        char test = 'c';
        byte b1 = (byte)99;
        byte b2 =(byte)0,b3 =(byte)0,b4 =(byte)0;
        byte[] ary = new byte[]{b2,b3,b4,b1};
        System.out.println(test);
        System.out.println((char)99);
        String s = new String(ary);
        byte[] ary2 = s.getBytes();
        System.out.println(s);
    }

    private void testRandomAccessFile() throws Exception{
        File file = new File("F:\\Abhishek\\Documents\\Resume\\Cover_letter.txt");
        RandomAccessFile rAFile = new RandomAccessFile(file, "rw");

        long pointer = rAFile.getFilePointer();
        FileDescriptor fds = rAFile.getFD();
        long fileLength = rAFile.length();
//        rAFile.seek(150);
//        String line = rAFile.readLine();
        while (pointer < fileLength-2){
//            char one = (char) rAFile.readChar();
            char one = (char) rAFile.read();
//            String one = rAFile.readLine();
            pointer = rAFile.getFilePointer();
            System.out.print(one);
        }


        System.out.println("");

    }
}
