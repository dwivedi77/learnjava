package abhi.learn.java.core.io;

import java.io.IOException;
import java.util.Arrays;

public class MyBufferredFile {

    private MyFile file = null;
    private int bufferSize;
    private byte[] storage = null;

    private int index;


    /**
     *
     * @param file
     * @param bufferSize no# of bytes.
     */
    public MyBufferredFile(MyFile file, int bufferSize){
        if (file == null || bufferSize <= 0)
            throw new IllegalArgumentException("Invalid argument supplied.");
        this.file = file;
        this.bufferSize = bufferSize;
        this.storage = new byte[bufferSize];
        index = 0;

    }

    public void write(String text) throws IOException {
        if (text == null || "".equals(text)) return;
        byte[] input = text.getBytes();
        write(input);
    }

    public void write(int oneBit) throws IOException {
        if (index < storage.length){
            storage[index++] = (byte)oneBit;
        }else{
            flush();
            index = 0;
        }
        flush();
    }

    public void write(byte[] input) throws IOException {
        if (input == null || input.length == 0) return;

        byte[] copy = Arrays.copyOf(input, input.length);

        for (int i = 0; i < copy.length; ) {
            if (index < storage.length){
                storage[index++] = copy[i++];
            }else{
                flush();
                index = 0;
            }
        }
        flush();
    }

    public void flush() throws IOException {
        file.write(storage);
        index = 0;
    }

    public void close(){
        file.close();
    }

}
