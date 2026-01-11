package General_Problems.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicatesFromAnUnsortedLinkedList {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();

        ListNode current = head;
        while( current != null ){
            int key = current.val;
            if( !map.containsKey(key)){
                map.put(key, 0);
            }
            
            map.put(key, map.get(key) + 1 );
            current = current.next;
        }

        current = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        while( current != null ){
            ListNode next = current.next;
            if( map.get(current.val) > 1 ){
                prev.next = next;
                current = next;
            } else {
                prev = current;
                current = current.next;
            }
        }

        return dummy.next;
    }
}
