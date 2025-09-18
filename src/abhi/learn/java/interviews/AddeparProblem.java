package abhi.learn.java.interviews;

import java.util.HashMap;
import java.util.Map;

public class AddeparProblem {

    private boolean locked = false;
    private Map<String, Map<String, String>> table = new HashMap<>();
    private Map<String, Map<String, String>> temp = null;
    public void createRow(String rowName){
        createTransaction();
        temp.put(rowName, new HashMap<>());
    }
    public void addRecord(String row, Map<String, String> rowData){
        if (!temp.containsKey(row)){
            temp.put(row, new HashMap<>());
        }
        rowData.forEach(
                (key, value) -> {
                    temp.get(row).put(key, value);
                }
        );
    }

    public void createTransaction(){
        locked = true;
        temp = new HashMap<>();

    }
    public void commitTransaction(){
        // copy the data from temp to main

        if (temp != null && !temp.isEmpty()){
            temp.forEach(
                    (rowName, rowData) -> {
                        if (!table.containsKey(rowName))
                            table.put(rowName, new HashMap<>());

                        rowData.forEach(
                                (colName, colVal) -> {
                                    table.get(rowName).put(colName, colVal);
                                }
                        );
                    }
            );
        }
        temp = null;
        locked = false;
    }
    public void rollbackTransaction(){
        temp = null;
        locked = false;
    }

    public void printTable(){
        
        table.forEach(
                (row, rowData) -> {
                    System.out.print("Row[["+row+"-");
                    System.out.print("Data[");
                    rowData.forEach(
                            (col, val) ->{
                                System.out.println(col + "==" + val);
                            }
                    );
                    System.out.print("]]");
                }
        );

        if (locked && temp != null)
            temp.forEach(
                    (row, rowData) -> {
                        System.out.print("Row[["+row+":");
                        System.out.print("Data[");
                        rowData.forEach(
                                (col, val) ->{
                                    System.out.print(col + "==" + val+", ");
                                }
                        );
                        System.out.print("]");
                        System.out.println("]]");
                    }
            );

    }

    public void test(){
        createTransaction();
        createRow("first");
        Map data = new HashMap(); data.put("col1", "val1"); data.put("col2", "val2");
        addRecord("first", data);
        data = new HashMap(); data.put("col1", "val1.1"); data.put("col2", "val2.1");
        addRecord("second", data);
        printTable();
        commitTransaction();
//        printTable();
    }
    public static void main(String[] args) {
        AddeparProblem add = new AddeparProblem();
        add.test();

    }
}
