package abhi.learn.java.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFile {

    private File file = null;
    private FileOutputStream outputStream = null;
    public MyFile(String filePath){
        if (filePath == null || "".equals(filePath.trim())) throw new RuntimeException("Can not create file with empty string.");
        file = new File(filePath);
    }

    public void write(byte[] content) throws IOException {
        /// writes to the disk.
        if (outputStream == null){
            outputStream = new FileOutputStream(file);
        }
        outputStream.write(content);
    }

    public void close(){
        try {
            outputStream.flush();
            outputStream.close();
        } catch (Throwable e) {

        } finally {
        }
    }
}
