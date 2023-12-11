package abhi.learn.java.datastructure.list;

/**
 * Created by Abhishek on 9/8/2016.
 */
public class ListMain {

    public static void main(String[] args) {
//        System.out.println(findLastNthElement(createLinkedList(), 4));
//        printLinkedList(createCircularLinkedList());
        Entry reversed = reverseLinkedList(createLinkedList());
        printLinkedList(reversed);

    }

    private static Entry reverseLinkedList(Entry node){
        if (node == null) return null;
        Entry prev = node;
        Entry curr = node.next(); prev.setNext(null);
        while (curr != null){
            Entry temp = curr.next();
            curr.setNext(prev);
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * For a node (e.g 1 > 2 > 3 > 4 > 5 > 6 > 7 > 8) and value of n=4
     * The output should be 6.
     * @param
     * @param n
     * @return
     */
    private static Entry findLastNthElement(Entry node, int n) {
        Entry temp = node; int i = 0;
        while (temp != null && i < n){
            temp = temp.next();i++;
        }
        while (temp.next() != null){
            node = node.next();
            temp = temp.next();
        }
        return node;
    }

    private static boolean isCircurlarList(Entry node){
        boolean result = false;
        if (node == null) return result;
        Entry next = node.next();
        if (next == null) return result;
        while (true){
            if(node.getValue() == next.getValue()){
                return true;
            }
            node = node.next();if (node == null) break;
            if(next.next() == null || next.next().next() == null) break;
            next = next.next().next();
        }

        return result;
    }

    private static void printLinkedList(Entry node){
        while (node != null){
            System.out.println("value="+node.getValue());
            node = node.next();
        }
    }

    /**
     * It return following lisked list. Its circular.
     * Printing this using printLinkedList will become an infinite loop.
     * 1 > 2 > 3 > 4 > 5 > 6 > 7 > 8 > 5
     * @return
     */
    private static Entry createCircularLinkedList(){
        Entry e1 = createLinkedList();
        Entry circle = e1.next().next().next().next();// 5
        Entry end = e1.next().next().next().next().next().next().next();//
        end.setNext(circle);
        return e1;
    }
    /**
     * It return following lisked list
     * 1 > 2 > 3 > 4 > 5 > 6 > 7 > 8
     * @return
     */
    private static Entry createLinkedList(){
        Entry e8 = new Entry(8, null);
        Entry e7 = new Entry(7, e8);
        Entry e6 = new Entry(6, e7);
        Entry e5 = new Entry(5, e6);
        Entry e4 = new Entry(4, e5);
        Entry e3 = new Entry(3, e4);
        Entry e2 = new Entry(2, e3);
        Entry e1 = new Entry(1, e2);
        return e1;
    }
}
