package abhi.learn.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Abhishek on 6/4/2016.
 */
public class DealerTrackerSolution {

    /**
     * Assumptions :
     * 1. There might be unwanted white spaces with key and/or count
     * 2. Keys has to be checked ignore case. i.e. John and joHn should be considered one key.
     * 3. Order of printing keys is same as they appear in file.
     *
     * @param filePath
     */
    private static void printKeyCounts(String filePath){
        System.out.println("Starting to scan the file ["+filePath+"] for key,count");
        Scanner scan = null;
        try {
            
            InputStream is = new FileInputStream(new File(filePath));
            scan = new Scanner(is);
            Map<String, Integer> cache = new LinkedHashMap<>(); // to print the output in predictable and consistent order(in the order as they appear in file)

            while (scan.hasNext()){
                try {
                    String nextLine = scan.nextLine();
                    int index = nextLine.indexOf(',');
                    if (index <= 0) continue; // ignoring the incorrect format lines
                    String name = nextLine.substring(0, index);

                    if(name == null || "".equals(name.trim())) continue;
                    name = name.trim().toUpperCase();

                    int count = Integer.parseInt(nextLine.substring(index+1).trim()); // this will throw error is the count substring is not a parsable integer value. And this line will be ignored.
                    if (cache.get(name) != null){
                        cache.put(name, cache.get(name)+count);
                    }else{
                        cache.put(name, count);
                    }
                }catch (Exception ex){
                    //any scanning, formatting error is ignored and scanner tries the next line
                }
            }
            StringBuilder builder = new StringBuilder();
            cache.forEach((key, value) -> builder.append("The total for "+key+" is "+value+". "));

            System.out.println("Print the output.");
            System.out.println(builder.toString());

        } catch (FileNotFoundException e) {// error in file io, print a message and exit.
            System.out.println("ERROR: Could not scan the file on given location. Exiting."+e.getLocalizedMessage());
            return;
        }  catch (Exception e) {// error in file io, print a message and exit.
            System.out.println("ERROR: Error in operation. Exiting."+e.getLocalizedMessage());
            return;
        } finally {
            if (scan != null){
                scan.close();
            }
        }
        System.out.println("Completed the scan and print operation. Exiting.");
    }

}
