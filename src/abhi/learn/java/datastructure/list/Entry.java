package abhi.learn.java.datastructure.list;

/**
 * A simple linked list
 * Created by Abhishek on 8/4/2016.
 */
public class Entry {
    public Entry(int value, Entry next) {
        this.value = value;
        this.next = next;
    }

    private int value;
    private Entry next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Entry next() {
        return next;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "value=" + value +
                '}';
    }
}
